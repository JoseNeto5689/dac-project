package br.edu.ifpb.projeto.services;

import br.edu.ifpb.projeto.dtos.ModalityDTO;
import br.edu.ifpb.projeto.exceptions.ModalityNotFoundException;
import br.edu.ifpb.projeto.models.Field;
import br.edu.ifpb.projeto.models.Modality;
import br.edu.ifpb.projeto.repositories.ModalityRepository;
import br.edu.ifpb.projeto.utils.GenericCRUDService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class ModalityService implements GenericCRUDService<Modality> {

    private final ModalityRepository modalityRepository;
    private final FieldService fieldService;

    public ModalityService(ModalityRepository modalityRepository, FieldService fieldService) {
        this.modalityRepository = modalityRepository;
        this.fieldService = fieldService;
    }

    @Override
    public Modality findById(UUID id) {
        return this.modalityRepository.findById(id).orElseThrow(() -> new ModalityNotFoundException(id));
    }

    @Override
    public List<Modality> findAll() {
        return this.modalityRepository.findAll();
    }

    @Override
    public Modality save(Modality entity) {
        return this.modalityRepository.save(entity);
    }

    public Modality save(ModalityDTO entity) {
        var modality = new Modality();
        var fields = entity.fields();
        List<Field> fieldList = new ArrayList<>();
        for (var field : fields) {
            field.setModality(modality);
            fieldList.add(field);
        }
        modality.setFields(fieldList);
        modality.setType(entity.type());
        return this.modalityRepository.save(modality);
    }


    @Override
    public void delete(Modality entity) {
        this.modalityRepository.delete(entity);
    }

    @Override
    public void delete(UUID id) {
        this.modalityRepository.deleteById(id);
    }
}
