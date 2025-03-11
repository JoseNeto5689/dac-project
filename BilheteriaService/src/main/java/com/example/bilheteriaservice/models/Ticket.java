package com.example.bilheteriaservice.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.io.Serializable;
import java.util.UUID;

@Entity
public class Ticket implements Serializable {
    @Id
    private UUID ticketId;
    private String cpf;
    private Boolean valitated;

    public Ticket() {

    }

    public Ticket(UUID ticketId, String cpf, Boolean valitated) {
        this.ticketId = ticketId;
        this.cpf = cpf;
        this.valitated = valitated;
    }

    public Boolean getValitated() {
        return valitated;
    }

    public void setValitated(Boolean valitated) {
        this.valitated = valitated;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
