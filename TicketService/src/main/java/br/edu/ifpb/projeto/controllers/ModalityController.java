package br.edu.ifpb.projeto.controllers;

import br.edu.ifpb.projeto.dtos.ModalityDTO;
import br.edu.ifpb.projeto.models.Modality;
import br.edu.ifpb.projeto.services.FieldService;
import br.edu.ifpb.projeto.services.ModalityService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/modality")
public class ModalityController {

    private final ModalityService modalityService;


    public ModalityController(ModalityService modalityService) {
        this.modalityService = modalityService;
    }

    @PostMapping("/")
    public ResponseEntity<Modality> save(@RequestBody ModalityDTO modalityDTO) {
        Modality modalitySaved = modalityService.save(modalityDTO);
        return new ResponseEntity<>(modalitySaved, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<List<Modality>> findAll() {
        var modalityList = modalityService.findAll();
        return new ResponseEntity<>(modalityList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Modality> findById(@PathVariable UUID id) {
        var modality = modalityService.findById(id);
        return new ResponseEntity<>(modality, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        var modality = modalityService.findById(id);
        modalityService.delete(modality);
        return new ResponseEntity<>("Modalidy has been deleted", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Modality> update(@PathVariable UUID id, @RequestBody ModalityDTO modalityDTO) {
        var modality = modalityService.findById(id);
        BeanUtils.copyProperties(modalityDTO, modality);
        modalityService.save(modality);
        return new ResponseEntity<>(modality, HttpStatus.OK);
    }

}
