package com.example.bilheteriaservice.controllers;


import com.example.bilheteriaservice.dto.ResponseDTO;
import com.example.bilheteriaservice.dto.TicketRequestDTO;
import com.example.bilheteriaservice.models.Ticket;
import com.example.bilheteriaservice.services.TicketService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {
    private final TicketService ticketService;

    public TicketController( TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Ticket>> findAll() {
        return ResponseEntity.ok(ticketService.findAll());
    }

    @GetMapping("/check/")
    public ResponseEntity<String> findById(@RequestBody TicketRequestDTO dto) {
        var ticket = this.ticketService.findById(dto.ticketId());
        if(ticket == null || ticket.getCpf() == null || !ticket.getCpf().equals(dto.cpf())) {
            return ResponseEntity.status(400).body("Ticket not valid");
        }
        if(ticket.getValitated()){
            return ResponseEntity.status(400).body("Ticket is already valitated");
        }
        return ResponseEntity.ok("Ticket is valid");
    }

    @PatchMapping("/validate/")
    public ResponseEntity<String> validate(@RequestBody TicketRequestDTO dto) {
        var ticket = this.ticketService.findById(dto.ticketId());
        if(ticket == null || ticket.getCpf() == null || !ticket.getCpf().equals(dto.cpf())) {
            return ResponseEntity.status(400).body("Ticket not valid");
        }
        ticketService.confirmTicket(ticket);
        return ResponseEntity.ok("Ticket validated now");
    }






}
