package com.example.bilheteriaservice.services;

import com.example.bilheteriaservice.dto.TicketRequestDTO;
import com.example.bilheteriaservice.models.Ticket;
import com.example.bilheteriaservice.repositories.TicketRepository;
import com.example.bilheteriaservice.utils.GenericCRUDService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TicketService implements GenericCRUDService<Ticket> {

    private final TicketRepository repository;
    public TicketService(TicketRepository repository) {
        this.repository = repository;
    }


    public void confirmTicket(Ticket ticket) {
        var ticketFounded = findById(ticket.getTicketId());
        if(ticketFounded == null || ticketFounded.getValitated()) return;
        if(ticket.getCpf().equals(ticketFounded.getCpf())){
            ticket.setValitated(true);
            repository.save(ticket);
        };
    }


    @Override
    public Ticket findById(UUID id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public List<Ticket> findAll() {
        return repository.findAll();
    }

    @Override
    public Ticket save(Ticket entity) {
        return repository.save(entity);
    }

    public Ticket save(TicketRequestDTO ticket) {
        Ticket ticketEntity = new Ticket();
        ticketEntity.setValitated(false);
        BeanUtils.copyProperties(ticket, ticketEntity);
        return repository.save(ticketEntity);
    }

    @Override
    public void delete(Ticket entity) {
        repository.delete(entity);
    }

    @Override
    public void delete(UUID id) {
        repository.deleteById(id);
    }
}
