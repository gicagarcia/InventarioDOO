package br.edu.ifsp.inventariodoo.application.view.domain.usecases.category;

import br.edu.ifsp.inventariodoo.application.repository.inmemory.InMemoryCategoryDAO;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.usecases.category.CategoryDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.category.CreateCategoryUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.category.FindCategoryUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindCategoryUseCaseTest {
    private FindCategoryUseCase findCategoryUseCase;
    private CategoryDAO categoryDAO;
    private CreateCategoryUseCase createCategoryUseCase;
    @BeforeEach
    void config(){
        CategoryDAO categoryDAO = new InMemoryCategoryDAO();
        findCategoryUseCase = new FindCategoryUseCase(categoryDAO);
        createCategoryUseCase = new CreateCategoryUseCase(categoryDAO);
    }
    @Test
    void FindValidCategory() {
        Category category1 = new Category(1, "hardware", "centro", "aplicacao");
        assertTrue(findCategoryUseCase.findOne(1).isEmpty());
    }

    @Test
    void FindValidCategoryByName(){
        Category category1 = new Category(1,"hardware", "centro", "aplicacao");
        assertNotNull(findCategoryUseCase.findOneByName(category1.getName()));
    }

    @Test
    void FindInvalidCategory(){
        Category category1 = new Category(null,"hardware", "centro", "aplicacao");
        assertThrows(IllegalArgumentException.class, () -> findCategoryUseCase.findOne(category1.getId()));
    }
    @Test
    void FindInvalidCategoryByName(){
        Category category1 = new Category();
        assertThrows(IllegalArgumentException.class, () -> findCategoryUseCase.findOneByName(category1.getName()));
    }





}