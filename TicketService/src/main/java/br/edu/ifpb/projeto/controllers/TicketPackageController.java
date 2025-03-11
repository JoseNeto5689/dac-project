package br.edu.ifpb.projeto.controllers;


import br.edu.ifpb.projeto.dtos.BuyPackageDTO;
import br.edu.ifpb.projeto.dtos.TicketPackageDTO;
import br.edu.ifpb.projeto.models.Ticket;
import br.edu.ifpb.projeto.models.TicketPackage;
import br.edu.ifpb.projeto.services.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/ticket-package")
public class TicketPackageController {

    private final TicketPackageService ticketPackageService;

    public TicketPackageController(TicketPackageService ticketPackageService) {
        this.ticketPackageService = ticketPackageService;
    }

    @PostMapping("/")
    public TicketPackage save(@RequestBody TicketPackageDTO ticketPackageDTO) {
        return ticketPackageService.save(ticketPackageDTO);
    }

    @PostMapping("/quantity/{number}")
    public List<TicketPackage> save(@RequestBody TicketPackageDTO ticketPackageDTO, @PathVariable Integer number) {
        return ticketPackageService.save(ticketPackageDTO, number);
    }

    @PatchMapping("/buy/{id}")
    public ResponseEntity<List<Ticket>> buy(@PathVariable UUID id, @RequestBody BuyPackageDTO buyPackageDTO) {
        var tickets = this.ticketPackageService.buyPackage(id, buyPackageDTO);
        return ResponseEntity.ok(tickets);
    }
}
