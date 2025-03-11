package br.edu.ifpb.projeto.repositories;

import br.edu.ifpb.projeto.models.EventInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EventInfoRepository extends JpaRepository<EventInfo, UUID> {

}
