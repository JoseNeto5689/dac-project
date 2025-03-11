package br.edu.ifpb.projeto.dtos;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public record FindAndBuyTicketDTO(PromoTicketDTO ticketInfo, BuyTicketDTO response) implements Serializable {
}
