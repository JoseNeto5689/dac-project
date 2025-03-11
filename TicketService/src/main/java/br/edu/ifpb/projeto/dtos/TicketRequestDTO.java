package br.edu.ifpb.projeto.dtos;

import java.util.UUID;

public record TicketRequestDTO(UUID ticketId, String cpf) {}
