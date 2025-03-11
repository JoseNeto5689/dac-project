package com.example.bilheteriaservice.dto;

import java.util.UUID;

public record TicketRequestDTO(UUID ticketId, String cpf) {}
