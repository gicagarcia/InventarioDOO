package br.edu.ifsp.inventariodoo.domain.usecases.category;

import br.edu.ifsp.inventariodoo.application.repository.InMemoryCategoryDAO;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;

import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteCategoryUseCaseTest {
    private DeleteCategoryUseCase deleteCategoryUseCase;
    private CategoryDAO categoryDAO;
    private CreateCategoryUseCase createCategoryUseCase;

    @BeforeEach
    void config(){
        CategoryDAO categoryDAO = new InMemoryCategoryDAO();
        deleteCategoryUseCase = new DeleteCategoryUseCase(categoryDAO);
        createCategoryUseCase = new CreateCategoryUseCase(categoryDAO);
    }
    @Test
    void DeleteTrue(){
        Category category1 = new Category(1,"joao","sao carlos","aplication");
        createCategoryUseCase.insert(category1);
        assertTrue(deleteCategoryUseCase.delete(category1));
    }
    @Test
    void DeleteTrueid(){
        Category category1 = new Category(1,"joao","sao carlos","aplication");
        createCategoryUseCase.insert(category1);
        assertTrue(deleteCategoryUseCase.delete(category1.getId()));
    }

    @Test
    void DeleteFalse(){
        Category category = new Category();
        assertThrows(EntityNotFoundException.class, () -> deleteCategoryUseCase.delete(category));
    }
    @Test
    void DeleteFalseid(){
        Category category = new Category();
        assertThrows(EntityNotFoundException.class, () -> deleteCategoryUseCase.delete(category.getId()));
    }

}