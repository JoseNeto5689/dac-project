package br.edu.ifpb.projeto.dtos;

import java.util.UUID;

public record EmailRequestDTO (String email, UUID ticketId) {
}
