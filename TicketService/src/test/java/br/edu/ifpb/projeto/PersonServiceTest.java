package br.edu.ifpb.projeto;

import br.edu.ifpb.projeto.exceptions.PersonNotFoundException;
import br.edu.ifpb.projeto.models.Person;
import br.edu.ifpb.projeto.repositories.PersonRepository;
import br.edu.ifpb.projeto.services.PersonService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @InjectMocks
    private PersonService personService;

    @Mock
    private PersonRepository personRepository;

    @Test
    public void testGetPersonById() {

        UUID personId = UUID.randomUUID();
        Person person = new Person(personId, "John Doe", "john.doe@example.com", "password123", "123.456.789-00", new Date());

        when(personRepository.findById(personId)).thenReturn(Optional.of(person));

        Person result = personService.findById(personId);

        assertEquals(personId, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals("123.456.789-00", result.getCpf());
        assertEquals(person.getBirthday(), result.getBirthday());
    }

    @Test
    public void testGetPersonById_NotFound() {
        UUID personId = UUID.randomUUID();

        when(personRepository.findById(personId)).thenReturn(Optional.empty());

        PersonNotFoundException exception = assertThrows(PersonNotFoundException.class, () -> {
            personService.findById(personId);
        });

        assertEquals(String.format("Person with id %s not found", personId), exception.getMessage());
    }

    @Test
    public void testCreatePerson() {

        UUID personId = UUID.randomUUID();
        Person person = new Person(personId, "John Doe", "john.doe@example.com", "password123", "123.456.789-00", new Date());

        when(personRepository.save(person)).thenReturn(person);

        Person result = personService.save(person);

        assertEquals(personId, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
        assertEquals("123.456.789-00", result.getCpf());
        assertEquals(person.getBirthday(), result.getBirthday());
    }
}
