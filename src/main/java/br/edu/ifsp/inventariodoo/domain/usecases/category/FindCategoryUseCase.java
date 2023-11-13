package br.edu.ifsp.inventariodoo.domain.usecases.category;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindCategoryUseCase {
    private CategoryDAO categoryDAO;

    public FindCategoryUseCase(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public Optional<Category> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null");
        return categoryDAO.findOne(id);
    }

    public Optional<Category> findOneByName(String name){
        if(Validator.nullOrEmpty(name))
            throw new IllegalArgumentException("Name can not be null or empty");
        return categoryDAO.findByName(name);
    }
    public List<Category> findAll(){
        return categoryDAO.findAll();
    }
}
