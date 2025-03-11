package br.edu.ifpb.projeto.exceptions;

public class TicketPackageEmptyException extends RuntimeException {
    public TicketPackageEmptyException() {
        super("Ticket package is empty");
    }
}
