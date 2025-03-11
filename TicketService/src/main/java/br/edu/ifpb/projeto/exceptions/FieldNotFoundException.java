package br.edu.ifpb.projeto.exceptions;

import java.util.UUID;

public class FieldNotFoundException extends RuntimeException {

    public FieldNotFoundException(UUID id) {
        super(String.format("Field response not found for id %s", id));
    }
}
