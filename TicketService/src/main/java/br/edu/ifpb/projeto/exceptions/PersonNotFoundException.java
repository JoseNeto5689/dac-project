package br.edu.ifpb.projeto.exceptions;

import java.util.UUID;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(UUID id) {
        super(String.format("Person with id %s not found", id));
    }

    public PersonNotFoundException() {
        super("Person not found");
    }
}
