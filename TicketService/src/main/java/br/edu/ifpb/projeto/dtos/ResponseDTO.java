package br.edu.ifpb.projeto.dtos;

import java.io.Serializable;
import java.util.UUID;

public record ResponseDTO(UUID fieldID, String response) implements Serializable {
}
