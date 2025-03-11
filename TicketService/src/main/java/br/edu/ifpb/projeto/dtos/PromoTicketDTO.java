package br.edu.ifpb.projeto.dtos;

import java.io.Serializable;
import java.util.UUID;

public record PromoTicketDTO(UUID eventId, UUID modalityId, UUID eventInfoId) implements Serializable {
}
