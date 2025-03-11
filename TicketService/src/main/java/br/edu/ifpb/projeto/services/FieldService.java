package br.edu.ifpb.projeto.services;

import br.edu.ifpb.projeto.exceptions.FieldNotFoundException;
import br.edu.ifpb.projeto.models.Field;
import br.edu.ifpb.projeto.repositories.FieldRepository;
import br.edu.ifpb.projeto.utils.GenericCRUDService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class FieldService implements GenericCRUDService<Field> {

    private final FieldRepository fieldRepository;

    public FieldService(FieldRepository fieldRepository) {
        this.fieldRepository = fieldRepository;
    }

    @Override
    public Field findById(UUID id) {
        return fieldRepository.findById(id).orElseThrow(() -> new FieldNotFoundException(id));
    }

    @Override
    public List<Field> findAll() {
        return this.fieldRepository.findAll();
    }

    @Override
    public Field save(Field entity) {
        return this.fieldRepository.save(entity);
    }


    @Override
    public void delete(Field entity) {
        this.fieldRepository.delete(entity);
    }

    @Override
    public void delete(UUID id) {
        this.fieldRepository.deleteById(id);
    }
}
