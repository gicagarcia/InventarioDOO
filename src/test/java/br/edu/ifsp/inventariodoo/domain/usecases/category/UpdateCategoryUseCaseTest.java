package br.edu.ifsp.inventariodoo.domain.usecases.category;

import br.edu.ifsp.inventariodoo.application.repository.InMemoryCategoryDAO;
import br.edu.ifsp.inventariodoo.application.repository.InMemoryPersonDAO;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.person.CreatePersonUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.person.DeletePersonUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.person.PersonDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateCategoryUseCaseTest {
    private UpdateCategoryUseCase updateCategoryUseCase;
    private CategoryDAO categoryDAO;
    private CreateCategoryUseCase createCategoryUseCase;

    @BeforeEach
    void config(){
        CategoryDAO categoryDAO = new InMemoryCategoryDAO();
        updateCategoryUseCase = new UpdateCategoryUseCase(categoryDAO);
        createCategoryUseCase = new CreateCategoryUseCase(categoryDAO);
    }

    @Test
    void UpdateTrue(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        createCategoryUseCase.insert(category1);
        category1.setArea("departamento");
        assertTrue(updateCategoryUseCase.update(category1));
    }

    @Test
    void UpdateFalseApplication(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        createCategoryUseCase.insert(category1);
        category1.setApplication("");
        assertThrows(IllegalArgumentException.class, () -> updateCategoryUseCase.update(category1));
    }

}