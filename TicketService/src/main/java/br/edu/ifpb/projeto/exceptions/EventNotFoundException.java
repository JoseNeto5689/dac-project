package br.edu.ifpb.projeto.exceptions;

import java.util.UUID;

public class EventNotFoundException extends RuntimeException {
    public EventNotFoundException(UUID id) {
        super(String.format("Event with id %s not found", id));
    }
}
