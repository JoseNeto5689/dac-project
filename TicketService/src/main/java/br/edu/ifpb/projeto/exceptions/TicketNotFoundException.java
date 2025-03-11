package br.edu.ifpb.projeto.exceptions;

import java.util.UUID;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(UUID message) {
        super(String.format("Ticket not found: %s", message));
    }
}
