package br.edu.ifsp.inventariodoo.domain.usecases.person;

import br.edu.ifsp.inventariodoo.application.repository.InMemoryCategoryDAO;
import br.edu.ifsp.inventariodoo.application.repository.InMemoryPersonDAO;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.category.CategoryDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.category.CreateCategoryUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.category.UpdateCategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdatePersonUseCaseTest {
    private UpdatePersonUseCase updatePersonUseCase;
    private PersonDAO personDAO;
    private CreatePersonUseCase createPersonUseCase;

    @BeforeEach
    void config(){
        PersonDAO personDAO = new InMemoryPersonDAO();
        updatePersonUseCase = new UpdatePersonUseCase(personDAO);
        createPersonUseCase = new CreatePersonUseCase(personDAO);
    }

    @Test
    void UpdateTrue(){
        Person person1 = Person.asPerson("","Maria joaquina","sla@gmail","123");
        createPersonUseCase.insert(person1);
        person1.setEmail("oi@gmail.com");
        assertTrue(updatePersonUseCase.update(person1));
    }

    @Test
    void UpdateFalseApplication(){
        Person person1 = Person.asPerson("","Maria joaquina","sla@gmail","123");
        createPersonUseCase.insert(person1);
        person1.setEmail("");
        assertThrows(IllegalArgumentException.class, () -> updatePersonUseCase.update(person1));
    }

}