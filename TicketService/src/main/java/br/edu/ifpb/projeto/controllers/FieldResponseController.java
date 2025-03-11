package br.edu.ifpb.projeto.controllers;
import br.edu.ifpb.projeto.dtos.FieldResponseDTO;
import br.edu.ifpb.projeto.models.FieldResponse;
import br.edu.ifpb.projeto.services.FieldResponseService;
import br.edu.ifpb.projeto.services.TicketService;
import br.edu.ifpb.projeto.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/ticket")
public class FieldResponseController {
    private final FieldResponseService fieldResponseService;
    private final TicketService ticketService;

    public FieldResponseController(FieldResponseService fieldResponseService, TicketService ticketService) {
        this.fieldResponseService = fieldResponseService;
        this.ticketService = ticketService;
    }

    //Jogar metodo para TicketController
    @PostMapping("/{ticket_id}/field-response")
    public ResponseEntity<FieldResponse> saveFieldResponse(@RequestBody FieldResponse fieldResponse, @PathVariable UUID ticket_id) {
        var ticket = ticketService.findById(ticket_id);
        var savedFieldResponse = fieldResponseService.save(fieldResponse);
        return ResponseEntity.ok().body(savedFieldResponse);
    }

    @GetMapping("/{ticket_id}/field-response")
    public ResponseEntity<List<FieldResponse>> getAllFieldResponse(@PathVariable UUID ticket_id) {
        var ticket = ticketService.findById(ticket_id);
        return ResponseEntity.ok().body(ticket.getResponseList());
    }

    @PutMapping("/{ticket_id}/field-response/{field_response_id}")
    public ResponseEntity<FieldResponse> updateFieldResponse(@PathVariable UUID ticket_id, @PathVariable UUID field_response_id,@RequestBody FieldResponseDTO fieldResponseDTO) {
        var ticket = ticketService.findById(ticket_id);
        var fieldResponse = fieldResponseService.findById(field_response_id);
        if(ticket.getResponseList().contains(fieldResponse)) {
            BeanUtils.copyProperties(fieldResponseDTO, fieldResponse, ObjectUtils.getNullPropertyNames(fieldResponseDTO));
            fieldResponseService.save(fieldResponse);
            return ResponseEntity.ok().body(fieldResponse);
        }
        return ResponseEntity.notFound().build();
    }
}
