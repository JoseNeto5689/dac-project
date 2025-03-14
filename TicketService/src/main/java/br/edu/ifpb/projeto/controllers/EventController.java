package br.edu.ifpb.projeto.controllers;

import br.edu.ifpb.projeto.dtos.EventCreateDTO;
import br.edu.ifpb.projeto.dtos.EventDTO;
import br.edu.ifpb.projeto.models.Event;
import br.edu.ifpb.projeto.models.EventInfo;
import br.edu.ifpb.projeto.producer.EmailRequestProducer;
import br.edu.ifpb.projeto.services.EventInfoService;
import br.edu.ifpb.projeto.services.EventService;
import br.edu.ifpb.projeto.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/events")
public class EventController {
    private final EventService eventService;
    private final EventInfoService eventInfoService;

    public EventController(EventService eventService, EventInfoService eventInfoService) {
        this.eventService = eventService;
        this.eventInfoService = eventInfoService;
    }

    @PostMapping("/date")
    public ResponseEntity<EventInfo> createDate(@RequestBody EventInfo date) {
        return new ResponseEntity<>(eventInfoService.save(date), HttpStatus.CREATED);
    }


    @PostMapping
    public ResponseEntity<Event> saveEvent(@RequestBody EventCreateDTO event) {
        var savedEvent = eventService.save(event);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedEvent);
    }

    @GetMapping
    public List<Event> getAllEvents() {
        return eventService.findAll();
    }

    @GetMapping("/{id}")
    public Event getEventById(@PathVariable UUID id) {
        return eventService.findById(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable UUID id, @RequestBody EventDTO eventDTO) {
        var event = eventService.findById(id);
        BeanUtils.copyProperties(eventDTO, event, ObjectUtils.getNullPropertyNames(eventDTO));
        eventService.save(event);
        return ResponseEntity.ok(event);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@PathVariable UUID id) {
        eventService.delete(id);
    }

}
