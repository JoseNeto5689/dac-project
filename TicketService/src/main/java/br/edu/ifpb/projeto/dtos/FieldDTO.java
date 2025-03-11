package br.edu.ifpb.projeto.dtos;

import java.io.Serializable;

public record FieldDTO(String description, String name, String type) implements Serializable {

}
