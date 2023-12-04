package br.edu.ifsp.inventariodoo.application.view.domain.usecases.person;

import br.edu.ifsp.inventariodoo.application.repository.inmemory.InMemoryPersonDAO;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.person.FindPersonUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.person.PersonDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindPersonUseCaseTest {
    private FindPersonUseCase findPersonUseCase;
    private PersonDAO personDAO;

    @BeforeEach
    void config(){
        PersonDAO personDAO = new InMemoryPersonDAO();
        findPersonUseCase = new FindPersonUseCase(personDAO);

    }
    @Test
    void FindValidPerson(){
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        assertNotNull(findPersonUseCase.findOne(person1.getRegistrationId()));
    }
    @Test
    void FindValidPersonByEmail(){
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        assertNotNull(findPersonUseCase.findOneByEmail(person1.getEmail()));
    }

    @Test
    void FindInvalidPerson(){
        Person person1 = Person.asPerson(null,"Maria joaquina","sla@gmail","123");
        assertThrows(IllegalArgumentException.class, () -> findPersonUseCase.findOne(person1.getRegistrationId()));
    }
    @Test
    void FindInvalidPersonByEmail(){
        Person person1 = Person.asPerson("123","Maria joaquina",null,"123");
        assertThrows(IllegalArgumentException.class, () -> findPersonUseCase.findOneByEmail(person1.getEmail()));
    }

}