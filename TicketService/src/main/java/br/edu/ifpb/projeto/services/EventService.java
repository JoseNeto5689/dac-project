package br.edu.ifpb.projeto.services;

import br.edu.ifpb.projeto.exceptions.EventNotFoundException;
import br.edu.ifpb.projeto.models.Event;
import br.edu.ifpb.projeto.repositories.EventRepository;
import br.edu.ifpb.projeto.utils.GenericCRUDService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventService implements GenericCRUDService<Event> {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Event save(Event event) {
        return eventRepository.save(event);
    }

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Event findById(UUID id) {
        return eventRepository.findById(id).orElseThrow(() -> new EventNotFoundException(id));
    }

    public void delete(Event entity) {
        eventRepository.delete(entity);
    }

    public void delete(UUID id) {
        eventRepository.deleteById(id);
    }

}
