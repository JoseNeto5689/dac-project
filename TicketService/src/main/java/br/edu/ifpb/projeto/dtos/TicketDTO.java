package br.edu.ifpb.projeto.dtos;

import br.edu.ifpb.projeto.models.*;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record TicketDTO(UUID eventId, UUID eventInfoId, UUID modalityId, UUID ownerID, List<ResponseDTO> fields) implements Serializable { }
