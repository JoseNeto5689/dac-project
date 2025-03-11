package br.edu.ifpb.projeto.services;

import br.edu.ifpb.projeto.dtos.*;
import br.edu.ifpb.projeto.exceptions.ProblemFillingTicketException;
import br.edu.ifpb.projeto.exceptions.TicketNotFoundException;
import br.edu.ifpb.projeto.models.FieldResponse;
import br.edu.ifpb.projeto.models.Modality;
import br.edu.ifpb.projeto.models.PromoTicket;
import br.edu.ifpb.projeto.models.Ticket;
import br.edu.ifpb.projeto.producer.EmailRequestProducer;
import br.edu.ifpb.projeto.producer.TicketRequestProducer;
import br.edu.ifpb.projeto.repositories.TicketRepository;
import br.edu.ifpb.projeto.utils.GenericCRUDService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService implements GenericCRUDService<Ticket> {

    private final TicketRepository repository;

    private final EventInfoService eventInfoService;
    private final EventService eventService;
    private final ModalityService modalityService;
    private final PersonService personService;
    private final TicketRequestProducer ticketRequestProducer;
    private final EmailRequestProducer emailRequestProducer;

    public TicketService(TicketRepository repository, EventInfoService eventInfoService, EventService eventService, ModalityService modalityService, PersonService personService, TicketRequestProducer ticketRequestProducer, EmailRequestProducer emailRequestProducer) {
        this.repository = repository;
        this.eventInfoService = eventInfoService;
        this.eventService = eventService;
        this.modalityService = modalityService;
        this.personService = personService;
        this.ticketRequestProducer = ticketRequestProducer;
        this.emailRequestProducer = emailRequestProducer;
    }

    public Ticket findTicket(UUID event, UUID eventDate, UUID modality){
        var tickets = this.repository.findByEvent_IdAndEventDate_IdAndModality_IdAndOwnerIsNull(event, eventDate, modality);
        if(tickets.isEmpty()){
            throw new RuntimeException("Ticket not found for package");
        }
        return tickets.get(0);
    }

    public List<Ticket> findOwnerTickets(UUID personId){
        var tickets = this.repository.findByOwner_Id(personId);
        if(tickets.isEmpty()){
            throw new RuntimeException("Ticket not found for package");
        }
        return tickets;
    }


    public static List<FieldResponse> fillTicket(List<ResponseDTO> responseDTOList, Modality modality){
        System.out.println(modality);
        if(responseDTOList.isEmpty()){
            throw new ProblemFillingTicketException("Response list is empty");
        }
        var fieldsResponded = 0;
        List<FieldResponse> fieldResponses = new ArrayList<>();
        var modalityFields = modality.getFields();
        for (var field : modalityFields) {
            for (var response : responseDTOList) {
                if(field.getId().equals(response.fieldID())){
                    var res = new FieldResponse();
                    res.setField(field);
                    res.setContent(response.response());
                    fieldResponses.add(res);
                    fieldsResponded++;
                    break;
                }
            }
        }
        System.out.println(modalityFields.size());
        if(fieldsResponded < modalityFields.size()){
            throw new ProblemFillingTicketException("Some fields were not filled");
        }

        return fieldResponses;
    }

    @Override
    public Ticket findById(UUID id) {
        return this.repository.findById(id).orElseThrow(() -> new TicketNotFoundException(id));
    }

    @Override
    public List<Ticket> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Ticket save(Ticket entity) {
        return this.repository.save(entity);
    }

    public List<Ticket> saveAll(List<Ticket> entities) {
        return this.repository.saveAll(entities);
    }

    public Ticket save(TicketDTO dto){
        var ticket = defineTicket(dto);
        return this.repository.save(ticket);
    }

    public List<Ticket> save(TicketDTO dto, Integer quantity){
        var ticket = defineTicket(dto);
        List<Ticket> ticketArray = new ArrayList<>();
        for(var i = 0; i < quantity; i++){
            var newTicket = new Ticket();
            BeanUtils.copyProperties(ticket, newTicket);
            ticketArray.add(newTicket);
        }
        return this.repository.saveAll(ticketArray);
    }

    private Ticket defineTicket(TicketDTO dto) {
        var eventInfo = eventInfoService.findById(dto.eventInfoId());
        var event = eventService.findById(dto.eventId());
        var modality = modalityService.findById(dto.modalityId());
        var ticket = new Ticket();
        var responses = new ArrayList<FieldResponse>();
        ticket.setEvent(event);
        ticket.setEventDate(eventInfo);
        ticket.setModality(modality);
        ticket.setResponseList(responses);
        return ticket;
    }

    @Override
    public void delete(Ticket entity) {
        this.repository.delete(entity);
    }

    @Override
    public void delete(UUID id) {
        this.repository.deleteById(id);
    }

    public Ticket buyTicket(UUID id,BuyTicketDTO buyTicketDTO) {
        var person = personService.findById(buyTicketDTO.ownerId());
        var ticket = this.findById(id);
        if(ticket.getOwner() != null){
            throw new RuntimeException("Ticket already purchased");
        }

        var responses = fillTicket(buyTicketDTO.fields(), ticket.getModality());
        ticket.setOwner(person);
        ticket.setId(id);
        ticket.setResponseList(responses);
        var request = new TicketRequestDTO(ticket.getId(), person.getCpf());
        ticketRequestProducer.integrate(request);
        emailRequestProducer.integrate(new EmaIlDTO(person.getEmail(), String.format("Ticket comprado com sucesso, id: %s", ticket.getId())));
        return this.save(ticket);
    }

    public Ticket buyTicket(PromoTicketDTO promoTicket, BuyTicketDTO buyTicketDTO) {
        var person = personService.findById(buyTicketDTO.ownerId());
        var ticket = this.findTicket(promoTicket.eventId(), promoTicket.eventInfoId(), promoTicket.modalityId());
        if(ticket.getOwner() != null){
            throw new RuntimeException("Ticket already purchased");
        }

        var responses = fillTicket(buyTicketDTO.fields(), ticket.getModality());
        ticket.setOwner(person);
        ticket.setResponseList(responses);
        var request = new TicketRequestDTO(ticket.getId(), person.getCpf());
        ticketRequestProducer.integrate(request);
        emailRequestProducer.integrate(new EmaIlDTO(person.getEmail(), String.format("Ticket comprado com sucesso, id: %s", ticket.getId())));
        return this.save(ticket);
    }

}
