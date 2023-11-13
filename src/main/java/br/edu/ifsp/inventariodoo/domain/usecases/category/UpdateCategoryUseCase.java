package br.edu.ifsp.inventariodoo.domain.usecases.category;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class UpdateCategoryUseCase {
    private CategoryDAO categoryDAO;

    public UpdateCategoryUseCase(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public boolean update(Category category){
        Validator<Category> validator = new CategoryInputRequestValidator();
        Notification notification = validator.validate(category);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer id = category.getId();
        if(categoryDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Category not found");

        return categoryDAO.update(category);
    }
}
