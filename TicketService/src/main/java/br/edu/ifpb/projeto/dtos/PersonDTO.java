package br.edu.ifpb.projeto.dtos;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

public record PersonDTO(String name, String email, String password, String cpf, Date birthday) implements Serializable {}

