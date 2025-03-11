package br.edu.ifpb.projeto.controllers;

import br.edu.ifpb.projeto.dtos.FieldDTO;
import br.edu.ifpb.projeto.models.Field;
import br.edu.ifpb.projeto.services.FieldService;
import br.edu.ifpb.projeto.services.ModalityService;
import br.edu.ifpb.projeto.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/modality")
public class FieldController {
    private final FieldService fieldService;
    private final ModalityService modalityService;

    public FieldController(FieldService fieldService, ModalityService modalityService) {
        this.fieldService = fieldService;
        this.modalityService = modalityService;
    }

    @GetMapping("/{modality_id}/field")
    public ResponseEntity<List<Field>> list(@PathVariable UUID modality_id) {
        modalityService.findById(modality_id);
        var fields = fieldService.findAll();
        List<Field> response = new ArrayList<>();
        for (Field field : fields) {
            if (field.getModality().getId().equals(modality_id)) {
                response.add(field);
            }
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{modality_id}/field/{field_id}")
    public ResponseEntity<Field> list(@PathVariable UUID modality_id, @PathVariable UUID field_id) {
        modalityService.findById(modality_id);
        var field = fieldService.findById(field_id);
        if (field.getModality().getId().equals(modality_id)) {
            return ResponseEntity.ok(field);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/{modality_id}/field")
    public ResponseEntity<Field> saveField(@RequestBody Field field, @PathVariable UUID modality_id) {
        var modality = modalityService.findById(modality_id);
        Field savedField = fieldService.save(field);
        savedField.setModality(modality);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedField);
    }

    @DeleteMapping("/{modality_id}/field/{field_id}")
    public ResponseEntity<Field> deleteField(@PathVariable UUID field_id, @PathVariable UUID modality_id) {
        var field = fieldService.findById(field_id);
        if(field.getModality().getId().equals(modality_id)) {
            fieldService.delete(field);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{modality_id}/field/{field_id}")
    public ResponseEntity<Field> updateField(@PathVariable UUID field_id, @PathVariable UUID modality_id, @RequestBody FieldDTO fieldDTO) {
        modalityService.findById(modality_id);
        var field = fieldService.findById(field_id);
        if(field.getModality().getId().equals(modality_id)) {
            BeanUtils.copyProperties(fieldDTO, field, ObjectUtils.getNullPropertyNames(fieldDTO));
            fieldService.save(field);
            return ResponseEntity.ok(field);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
