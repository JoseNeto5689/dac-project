package br.edu.ifpb.projeto.exceptions;

import java.util.UUID;

public class EventInfoNotFoundException extends RuntimeException {
    public EventInfoNotFoundException(UUID id) {
        super(String.format("EventInfo with id %s not found", id));
    }
}
