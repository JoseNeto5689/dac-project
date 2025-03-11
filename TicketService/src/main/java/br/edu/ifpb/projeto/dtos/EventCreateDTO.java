package br.edu.ifpb.projeto.dtos;

import br.edu.ifpb.projeto.models.Organizer;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public record EventCreateDTO (String name, String description, List<UUID> dates, List<Organizer> organizers) implements Serializable {}
