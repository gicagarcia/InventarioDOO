package br.edu.ifsp.inventariodoo.application.view.domain.usecases.person;

import br.edu.ifsp.inventariodoo.application.repository.inmemory.InMemoryPersonDAO;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.person.CreatePersonUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.person.PersonDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatePersonUseCaseTest {
    private CreatePersonUseCase createPersonUseCase;
    private PersonDAO personDAO;

    @BeforeEach
    void config(){
        PersonDAO personDAO = new InMemoryPersonDAO();
        createPersonUseCase = new CreatePersonUseCase(personDAO);
    }

    @Test
    void CreateValidPerson(){
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        assertNotNull(createPersonUseCase.insert(person1));
    }

    @Test
    void CreatePersonWithoutRegistration(){
        Person person1 = Person.asPerson("","Maria joaquina","sla@gmail","123");
        assertThrows(IllegalArgumentException.class, () -> createPersonUseCase.insert(person1));
    }

    @Test
    void CreatePersonWithoutName(){
        Person person1 = Person.asPerson("123","","sla@gmail","123");
        assertThrows(IllegalArgumentException.class, () -> createPersonUseCase.insert(person1));
    }

    @Test
    void CreatePersonWithoutEmail(){
        Person person1 = Person.asPerson("123","Maria joaquina","","123");
        assertThrows(IllegalArgumentException.class, () -> createPersonUseCase.insert(person1));
    }

    @Test
    void CreatePersonWithoutPhone(){
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","");
        assertThrows(IllegalArgumentException.class, () -> createPersonUseCase.insert(person1));
    }

    @Test
    void CreateUsersWithoutPassword(){
        Person person1 = Person.asWarehouseman("123","Maria joaquina","sla@gmail","123", "");
        assertThrows(IllegalArgumentException.class, () -> createPersonUseCase.insert(person1));

        Person person2 = Person.asPremier("234","Maria joaquina","sla@gmail","123", "");
        assertThrows(IllegalArgumentException.class, () -> createPersonUseCase.insert(person2));
    }


}