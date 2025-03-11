package br.edu.ifpb.projeto.exceptions;

import java.util.UUID;

public class ModalityNotFoundException extends RuntimeException {
    public ModalityNotFoundException(UUID id) {
        super(String.format("Modality %s not found", id));
    }
}
