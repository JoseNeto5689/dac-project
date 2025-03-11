package br.edu.ifpb.projeto.exceptions;

import java.util.UUID;

public class FieldResponseNotFoundException extends RuntimeException {
    public FieldResponseNotFoundException(UUID id) {
        super(String.format("Field response not found for id %s", id));
    }
}
