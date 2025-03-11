package com.example.bilheteriaservice.repositories;

import com.example.bilheteriaservice.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {
}
