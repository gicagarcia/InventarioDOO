package br.edu.ifsp.inventariodoo.application.view.domain.usecases.category;

import br.edu.ifsp.inventariodoo.application.repository.inmemory.InMemoryCategoryDAO;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.usecases.category.CategoryDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.category.CreateCategoryUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.category.UpdateCategoryUseCase;
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