package br.edu.ifpb.projeto.dtos;

import java.io.Serializable;
import java.util.List;

public record BuyPackageDTO(List<BuyTicketDTO> ticketsInfo) implements Serializable {
}
