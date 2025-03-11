package br.edu.ifpb.projeto.exceptions;

import java.util.UUID;

public class OrganizerNotFoundException extends RuntimeException {
    public OrganizerNotFoundException(UUID id) {
        super(String.format("Organizer with id %s not found", id));
    }
}
