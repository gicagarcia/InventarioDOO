package br.edu.ifsp.inventariodoo.application.view.domain.usecases.category;

import br.edu.ifsp.inventariodoo.application.repository.InMemoryCategoryDAO;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.usecases.category.CategoryDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.category.CreateCategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateCategoryUseCaseTest {
    private CreateCategoryUseCase createCategoryUseCase;
    private CategoryDAO categoryDAO;

    @BeforeEach
    void config(){
        CategoryDAO categoryDAO = new InMemoryCategoryDAO();
        createCategoryUseCase = new CreateCategoryUseCase(categoryDAO);
    }

    @Test
    void CreateValidCategory(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        assertNotNull(createCategoryUseCase.insert(category1));
    }

    @Test
    void CreateCategoryWithoutName(){
        Category category1 = new Category("", "centro", "aplicacao");
        assertThrows(IllegalArgumentException.class, () -> createCategoryUseCase.insert(category1));
    }
    @Test
    void CreateCategoryWithoutArea(){
        Category category1 = new Category("hardware", "", "aplicacao");
        assertThrows(IllegalArgumentException.class, () -> createCategoryUseCase.insert(category1));
    }
    @Test
    void CreateCategoryWithoutApplication(){
        Category category1 = new Category("hardware", "centro", "");
        assertThrows(IllegalArgumentException.class, () -> createCategoryUseCase.insert(category1));
    }

}