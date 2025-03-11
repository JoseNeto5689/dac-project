package br.edu.ifpb.projeto.controllers;

import br.edu.ifpb.projeto.models.Organizer;
import br.edu.ifpb.projeto.services.OrganizerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/api/organizers")
public class OrganizerController {

    private final OrganizerService organizerService;

    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @PostMapping
    public ResponseEntity<Organizer> saveOrganizers(@RequestBody Organizer organizer) {
        Organizer savedOrganizer = organizerService.save(organizer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedOrganizer);
    }

    @GetMapping
    public List<Organizer> getAllOrganizers() {
        return organizerService.findAll();
    }

    @GetMapping("/{id}")
    public Organizer getOrganizerById(@PathVariable UUID id) {
        return organizerService.findById(id);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrganizer(@PathVariable Organizer id) {
        organizerService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
