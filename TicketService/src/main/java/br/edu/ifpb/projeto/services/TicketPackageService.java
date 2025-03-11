package br.edu.ifpb.projeto.services;

import br.edu.ifpb.projeto.dtos.*;
import br.edu.ifpb.projeto.exceptions.ProblemFillingTicketException;
import br.edu.ifpb.projeto.exceptions.TicketPackageEmptyException;
import br.edu.ifpb.projeto.exceptions.TicketPackageNotFoundException;
import br.edu.ifpb.projeto.models.PromoTicket;
import br.edu.ifpb.projeto.models.Ticket;
import br.edu.ifpb.projeto.models.TicketPackage;
import br.edu.ifpb.projeto.producer.EmailRequestProducer;
import br.edu.ifpb.projeto.producer.TicketRequestProducer;
import br.edu.ifpb.projeto.repositories.TicketPackageRepository;
import br.edu.ifpb.projeto.utils.GenericCRUDService;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;

@Service
public class TicketPackageService implements GenericCRUDService<TicketPackage> {

    private final TicketPackageRepository repository;

    private final EventService eventService;
    private final ModalityService modalityService;
    private final EventInfoService eventInfoService;
    private final TicketService ticketService;
    private final PersonService personService;
    private final TicketRequestProducer ticketRequestProducer;

    private final EmailRequestProducer emailRequestProducer;

    public TicketPackageService(TicketPackageRepository repository, EventService eventService, ModalityService modalityService, EventInfoService eventInfoService, TicketService ticketService, PersonService personService, TicketRequestProducer ticketRequestProducer, EmailRequestProducer emailRequestProducer) {
        this.repository = repository;
        this.eventService = eventService;
        this.modalityService = modalityService;
        this.eventInfoService = eventInfoService;
        this.ticketService = ticketService;
        this.personService = personService;
        this.ticketRequestProducer = ticketRequestProducer;
        this.emailRequestProducer = emailRequestProducer;
    }

    @Override
    public TicketPackage findById(UUID id) {
        return this.repository.findById(id).orElseThrow(() -> new TicketPackageNotFoundException(id));
    }

    @Override
    public List<TicketPackage> findAll() {
        return this.repository.findAll();
    }

    @Override
    public TicketPackage save(TicketPackage entity) {
        return this.repository.save(entity);
    }

    public List<Ticket> buyPackage(UUID id,BuyPackageDTO buyPackageDTO){
        var tickets = new ArrayList<Ticket>();
        var backup = new ArrayList<Ticket>();
        var personList = buyPackageDTO.ticketsInfo().stream().iterator();
        var ticketPackage = this.findById(id);
        var personEmails = new HashSet<EmailRequestDTO>();
        try {
            for (var ticketOption : ticketPackage.getTicketOptions()) {
                if (!personList.hasNext()) {
                    throw new TicketPackageEmptyException();
                }
                var info = personList.next();
                System.out.println(info);
                var ticket = this.ticketService.findTicket(ticketOption.getEvent().getId(), ticketOption.getEventDate().getId(), ticketOption.getModality().getId());
                backup.add(ticket);
                var person = this.personService.findById(info.ownerId());
                var responses = TicketService.fillTicket(info.fields(), ticketOption.getModality());
                personEmails.add(new EmailRequestDTO(person.getEmail(), ticket.getId()));
                ticket.setOwner(person);
                ticket.setResponseList(responses);
                var savedTicket = this.ticketService.save(ticket);
                tickets.add(savedTicket);
            }
        }
        catch (ProblemFillingTicketException e){
            backup.forEach(resetTickets());
            throw new TicketPackageEmptyException();
        }
        personEmails.forEach(sendEmail());
        tickets.forEach(sendTickets());

        if(tickets.isEmpty()){
            throw new TicketPackageEmptyException();
        }

        return tickets;
    }

    public Consumer<Ticket> sendTickets() {
        return ticket -> {
            var person = personService.findById(ticket.getOwner().getId());
            var request = new TicketRequestDTO(ticket.getId(), person.getCpf());
            ticketRequestProducer.integrate(request);
        };
    }

    public Consumer<Ticket> resetTickets() {
        return ticket -> {
            ticket.setOwner(null);
            this.ticketService.save(ticket);
        };
    }

    public Consumer<EmailRequestDTO> sendEmail() {
        return dto -> {
            emailRequestProducer.integrate(new EmaIlDTO(
                    dto.email(),
                    String.format("Ticket comprado com sucesso, id: %s", dto.ticketId())
            ));
        };
    }

    public TicketPackage save(TicketPackageDTO ticketPackageDTO) {
        var ticketPackage = fillPackage(ticketPackageDTO);
        return save(ticketPackage);
    }

    public List<TicketPackage> save(TicketPackageDTO ticketPackageDTO, Integer quantity) {
        List<TicketPackage> ticketPackageList = new ArrayList<>();
        for (var i = 0; i < quantity; i++) {
            var ticketPackage = fillPackage(ticketPackageDTO);
            var newPackage = new TicketPackage();
            BeanUtils.copyProperties(ticketPackage, newPackage);
            ticketPackageList.add(newPackage);
        }
        return this.repository.saveAll(ticketPackageList);
    }

    private TicketPackage fillPackage(TicketPackageDTO ticketPackageDTO) {
        if (ticketPackageDTO.ticketList().isEmpty()) {
            throw new TicketPackageEmptyException();
        }
        var ticketPackage = new TicketPackage();
        ticketPackage.setType(ticketPackageDTO.type());
        List<PromoTicket> ticketPackageList = ticketPackageDTO.ticketList().stream().map(convertPromoTicketDTO()).toList();
        ticketPackage.setTicketOptions(ticketPackageList);
        return ticketPackage;
    }

    public Function<PromoTicketDTO, PromoTicket> convertPromoTicketDTO() {
        return ticket -> {
            var promoTicket = new PromoTicket();
            var event = eventService.findById(ticket.eventId());
            var eventInfo = eventInfoService.findById(ticket.eventInfoId());
            var modality = modalityService.findById(ticket.modalityId());
            promoTicket.setEvent(event);
            promoTicket.setModality(modality);
            promoTicket.setEventDate(eventInfo);
            return promoTicket;
        };
    }

    @Override
    public void delete(TicketPackage entity) {
        this.repository.delete(entity);
    }

    @Override
    public void delete(UUID id) {
        this.repository.deleteById(id);
    }
}
