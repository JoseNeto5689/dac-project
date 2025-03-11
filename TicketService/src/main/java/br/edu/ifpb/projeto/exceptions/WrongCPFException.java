package br.edu.ifpb.projeto.exceptions;

import java.util.UUID;

public class WrongCPFException extends RuntimeException {

    public WrongCPFException() {
        super("CPF is invalid");
    }
}
