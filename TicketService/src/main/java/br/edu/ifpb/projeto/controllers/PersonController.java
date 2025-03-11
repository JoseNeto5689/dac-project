package br.edu.ifpb.projeto.controllers;


import br.edu.ifpb.projeto.dtos.PersonDTO;
import br.edu.ifpb.projeto.models.Person;
import br.edu.ifpb.projeto.models.Ticket;
import br.edu.ifpb.projeto.services.PersonService;
import br.edu.ifpb.projeto.services.TicketService;
import br.edu.ifpb.projeto.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;
    private final TicketService ticketService;

    public PersonController(PersonService personService, TicketService ticketService) {
        this.personService = personService;
        this.ticketService = ticketService;
    }

    @PostMapping
    public ResponseEntity<Person> savePerson(@RequestBody Person person) {
        Person savedPerson = personService.save(person);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPerson);
    }

    @GetMapping
    public List<Person> getAllPersons() {
        return personService.findAll();
    }

    @GetMapping("/{id}")
    public Person getPersonById(@PathVariable UUID id) {
        return personService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Person> updatePerson(@PathVariable UUID id, @RequestBody PersonDTO personDetails) {
        var person = personService.findById(id);
        BeanUtils.copyProperties(personDetails, person, ObjectUtils.getNullPropertyNames(personDetails));
        personService.save(person);
        return ResponseEntity.ok(person);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable Person id) {
        personService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/tickets/{id}")
    public List<Ticket> getTicketsByPerson(@PathVariable UUID id) {
        return this.ticketService.findOwnerTickets(id);
    }


}
