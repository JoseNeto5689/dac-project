package br.edu.ifpb.projeto.services;

import br.edu.ifpb.projeto.exceptions.PersonNotFoundException;
import br.edu.ifpb.projeto.models.Person;
import br.edu.ifpb.projeto.models.Ticket;
import br.edu.ifpb.projeto.repositories.PersonRepository;
import br.edu.ifpb.projeto.repositories.TicketRepository;
import br.edu.ifpb.projeto.utils.GenericCRUDService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PersonService implements GenericCRUDService<Person> {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Person save(Person person) {
        this.personRepository.save(person);
        return person;
    }

    public List<Person> findAll() {
        return this.personRepository.findAll();
    }

    public Person findById(UUID id) {
        return this.personRepository.findById(id).orElseThrow(() -> new PersonNotFoundException(id));
    }

    public void delete(Person person) {
        this.personRepository.delete(person);
    }

    @Override
    public void delete(UUID id) {
        this.personRepository.deleteById(id);
    }

}
