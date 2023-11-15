package br.edu.ifsp.inventariodoo.domain.usecases.person;

import br.edu.ifsp.inventariodoo.application.repository.InMemoryPersonDAO;
import br.edu.ifsp.inventariodoo.application.repository.InMemoryRegisterDAO;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.register.CreateRegisterUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.register.RegisterDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestTemplate;

import static org.junit.jupiter.api.Assertions.*;

class DeletePersonUseCaseTest {
    private DeletePersonUseCase deletePersonUseCase;
    private PersonDAO personDAO;
    private CreatePersonUseCase createPersonUseCase;

    @BeforeEach
    void config(){
        PersonDAO personDAO = new InMemoryPersonDAO();
        deletePersonUseCase = new DeletePersonUseCase(personDAO);
        createPersonUseCase = new CreatePersonUseCase(personDAO);
    }

    @Test
    void DeleteTrue(){
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        createPersonUseCase.insert(person1);
        assertTrue(deletePersonUseCase.delete(person1));
    }

    @Test
    void DeleteFalse(){
        Person person = new Person();
        assertThrows(EntityNotFoundException.class, () -> deletePersonUseCase.delete(person));
    }

}