package br.edu.ifsp.inventariodoo.domain.usecases.category;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class CreateCategoryUseCase {

    private CategoryDAO categoryDAO;

    public CreateCategoryUseCase(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    public Integer insert(Category category){
        Validator<Category> validator = new CategoryInputRequestValidator();
        Notification notification = validator.validate(category);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

//        Integer id = category.getId();
//        if(categoryDAO.findOne(id).isPresent())
//            throw new EntityAlreadyExistsException("This ID is already in use");
        
        return categoryDAO.create(category);
    }
}
