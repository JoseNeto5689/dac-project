package br.edu.ifpb.projeto.dtos;

import java.io.Serializable;
import java.time.LocalDate;

public record EventDTO (String name, String description) implements Serializable {

}
