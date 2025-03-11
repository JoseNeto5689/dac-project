package br.edu.ifpb.projeto.controllers;


import br.edu.ifpb.projeto.dtos.BuyTicketDTO;
import br.edu.ifpb.projeto.dtos.EmaIlDTO;
import br.edu.ifpb.projeto.dtos.FindAndBuyTicketDTO;
import br.edu.ifpb.projeto.dtos.TicketDTO;
import br.edu.ifpb.projeto.models.FieldResponse;
import br.edu.ifpb.projeto.models.Ticket;
import br.edu.ifpb.projeto.producer.EmailRequestProducer;
import br.edu.ifpb.projeto.services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static br.edu.ifpb.projeto.services.TicketService.fillTicket;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;


    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/")
    public ResponseEntity<Ticket> createTicket(@RequestBody TicketDTO ticketDTO) {
        var ticketResponse = ticketService.save(ticketDTO);
        return new ResponseEntity<>(ticketResponse,HttpStatus.CREATED);
    }

    @PostMapping("/quantity/{number}")
    public ResponseEntity<List<Ticket>> createTicket(@RequestBody TicketDTO ticketDTO, @PathVariable Integer number) {
        var ticketResponse = ticketService.save(ticketDTO, number);
        return new ResponseEntity<>(ticketResponse,HttpStatus.CREATED);
    }
    
    @PatchMapping("/{id}")
    public ResponseEntity<Object> buyTicket(@PathVariable("id") UUID id, @RequestBody BuyTicketDTO buyTicketDTO) {
        var ticketResponse = ticketService.buyTicket(id, buyTicketDTO);
        return new ResponseEntity<>(ticketResponse,HttpStatus.OK);

    }

    @PatchMapping("/buy")
    public ResponseEntity<Object> findAndBuyTicket( @RequestBody FindAndBuyTicketDTO dto) {
        var ticketResponse = ticketService.buyTicket(dto.ticketInfo(), dto.response());
        return new ResponseEntity<>(ticketResponse,HttpStatus.OK);

    }

}
