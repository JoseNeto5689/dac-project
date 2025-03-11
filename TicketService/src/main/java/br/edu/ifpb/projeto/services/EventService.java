package br.edu.ifpb.projeto.services;

import br.edu.ifpb.projeto.dtos.EventCreateDTO;
import br.edu.ifpb.projeto.exceptions.EventNotFoundException;
import br.edu.ifpb.projeto.models.Event;
import br.edu.ifpb.projeto.models.EventInfo;
import br.edu.ifpb.projeto.repositories.EventRepository;
import br.edu.ifpb.projeto.utils.GenericCRUDService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EventService implements GenericCRUDService<Event> {
    private final EventRepository eventRepository;
    private final EventInfoService eventInfoService;

    public EventService(EventRepository eventRepository, EventInfoService eventInfoService) {
        this.eventRepository = eventRepository;
        this.eventInfoService = eventInfoService;
    }

    public Event save(Event event) {
        var events = eventRepository.findAll();
        for (var eventFinded : events) {
            for (var eventInfo: event.getDate()){
                if(eventInfo.getId().equals(event.getId())){}
            }
        }
        return eventRepository.save(event);
    }

    public Event save(EventCreateDTO dto) {
        var event = new Event();
        event.setName(dto.name());
        event.setDescription(dto.description());
        event.setOrganizers(dto.organizers());
        List<EventInfo> eventInfos = new ArrayList<>();
        for (var dateId: dto.dates()){
            var eventInfo = this.eventInfoService.findById(dateId);
            eventInfos.add(eventInfo);
        }
        var events = eventRepository.findAll();
        for (var eventFound : events) {
            for (var eventInfo: eventFound.getDate()) {
                for (var dateId: dto.dates()){
                    if(eventInfo.getId().equals(dateId)){
                        throw new RuntimeException("This date already have an event");
                    }
                }
            }
        }
        event.setDate(eventInfos);
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
