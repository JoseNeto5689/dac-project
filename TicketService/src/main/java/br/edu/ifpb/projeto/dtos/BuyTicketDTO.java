package br.edu.ifpb.projeto.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record BuyTicketDTO(UUID ownerId, List<ResponseDTO> fields) implements Serializable {
}
