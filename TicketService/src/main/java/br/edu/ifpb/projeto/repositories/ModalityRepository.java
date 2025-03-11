package br.edu.ifpb.projeto.repositories;

import br.edu.ifpb.projeto.models.Modality;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ModalityRepository extends JpaRepository<Modality, UUID> {


}
