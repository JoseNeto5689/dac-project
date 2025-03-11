package br.edu.ifpb.projeto.repositories;

import br.edu.ifpb.projeto.models.Ticket;
import jakarta.persistence.Entity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<Ticket, UUID> {

    List<Ticket> findByEvent_IdAndEventDate_IdAndModality_IdAndOwnerIsNull(UUID event, UUID eventDate, UUID modality);
    List<Ticket> findByOwner_Id(UUID owner);
}
