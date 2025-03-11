package br.edu.ifpb.projeto.utils;

import br.edu.ifpb.projeto.dtos.ErrorDTO;
import br.edu.ifpb.projeto.exceptions.*;
import br.edu.ifpb.projeto.models.EventInfo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.io.FileNotFoundException;

@ControllerAdvice
public class ExceptionConverter {

    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO converter(EventNotFoundException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(EventInfoNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO converter(EventInfoNotFoundException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO converter(FieldNotFoundException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(FieldResponseAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO converter(FieldResponseAlreadyExistsException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(FieldResponseCantBeNullException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO converter(FieldResponseCantBeNullException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(ModalityNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO converter(ModalityNotFoundException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(TicketNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO converter(TicketNotFoundException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(TicketPackageEmptyException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO converter(TicketPackageEmptyException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(TicketPackageNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO converter(TicketPackageNotFoundException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(WrongCPFException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO converter(WrongCPFException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO converter(PersonNotFoundException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(OrganizerNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO converter(OrganizerNotFoundException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO converter(MethodArgumentTypeMismatchException e){
        return new ErrorDTO(e.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorDTO converter(RuntimeException e){
        return new ErrorDTO(e.getMessage());
    }
}
