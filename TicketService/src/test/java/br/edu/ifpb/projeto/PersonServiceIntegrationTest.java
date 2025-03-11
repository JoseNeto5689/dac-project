package br.edu.ifpb.projeto;

import br.edu.ifpb.projeto.models.Person;
import br.edu.ifpb.projeto.repositories.PersonRepository;
import br.edu.ifpb.projeto.services.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class PersonServiceIntegrationTest {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Test
    @Transactional
    public void testGetPersonById() {
        Person person = new Person(null, "John Doe", "john.doe@example.com", "password123", "123.456.789-00", new Date());

        personRepository.save(person);

        UUID personId = person.getId();

        assertNotNull(personId);

        Person result = personService.findById(personId);

        assertNotNull(result);
        assertEquals(personId, result.getId());
        assertEquals("John Doe", result.getName());
        assertEquals("john.doe@example.com", result.getEmail());
    }

    @Test
    @Transactional
    @Commit
    public void testUpdatePerson() {
        Person person = new Person(null, "John Doe", "john.doe@example.com", "password123", "123.456.789-00", new Date());

        personRepository.save(person);

        UUID personId = person.getId();

        person.setName("Jane Doe");
        person.setEmail("jane.doe@example.com");

        personRepository.save(person);

        Person updatedPerson = personService.findById(personId);

        assertNotNull(updatedPerson);
        assertEquals("Jane Doe", updatedPerson.getName());
        assertEquals("jane.doe@example.com", updatedPerson.getEmail());
    }



}
