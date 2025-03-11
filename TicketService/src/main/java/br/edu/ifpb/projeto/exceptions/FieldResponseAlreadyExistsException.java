package br.edu.ifpb.projeto.exceptions;

import java.util.UUID;

public class FieldResponseAlreadyExistsException extends RuntimeException {
    public FieldResponseAlreadyExistsException(UUID id) {
    }
}
