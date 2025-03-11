package br.edu.ifpb.projeto.dtos;

import java.io.Serializable;
import java.util.List;

public record TicketPackageDTO(String type, List<PromoTicketDTO> ticketList) implements Serializable {}
