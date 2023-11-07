package br.edu.ifsp.inventariodoo.domain.usecases.category;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;

public class DeleteCategoryUseCase {

    private CategoryDAO categoryDAO;

    public DeleteCategoryUseCase(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public boolean delete(Integer id){
        if(id == null || categoryDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Category not found");
        return categoryDAO.deleteByKey(id);

    }

    public boolean delete(Category category){
        if(category == null || categoryDAO.findOne(category.getId()).isEmpty())
            throw new EntityNotFoundException("Category not found");
        return categoryDAO.delete(category);
    }
}
