package br.edu.ifpb.projeto.repositories;

import br.edu.ifpb.projeto.models.TicketPackage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicketPackageRepository extends JpaRepository<TicketPackage, UUID> {
}
