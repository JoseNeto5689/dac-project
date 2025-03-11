package br.edu.ifpb.projeto.services;

import br.edu.ifpb.projeto.exceptions.EventInfoNotFoundException;
import br.edu.ifpb.projeto.models.EventInfo;
import br.edu.ifpb.projeto.repositories.EventInfoRepository;
import br.edu.ifpb.projeto.utils.GenericCRUDService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class EventInfoService implements GenericCRUDService<EventInfo> {

    private final EventInfoRepository eventInfoRepository;

    public EventInfoService(EventInfoRepository repository) {
        this.eventInfoRepository = repository;
    }

    @Override
    public EventInfo findById(UUID id) {
        return eventInfoRepository.findById(id).orElseThrow(() -> new EventInfoNotFoundException(id));
    }

    @Override
    public List<EventInfo> findAll() {
        return eventInfoRepository.findAll();
    }

    @Override
    public EventInfo save(EventInfo entity) {
        return this.eventInfoRepository.save(entity);
    }

    @Override
    public void delete(EventInfo entity) {
        this.eventInfoRepository.delete(entity);
    }

    @Override
    public void delete(UUID id) {
        this.eventInfoRepository.deleteById(id);
    }
}
