package br.edu.ifpb.projeto.exceptions;

import java.util.UUID;

public class TicketPackageNotFoundException extends RuntimeException {
    public TicketPackageNotFoundException(UUID id) {
        super(String.format("Ticket package %s not found", id));
    }
}
