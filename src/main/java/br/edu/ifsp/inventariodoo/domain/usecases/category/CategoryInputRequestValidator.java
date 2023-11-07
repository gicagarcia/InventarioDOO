package br.edu.ifsp.inventariodoo.domain.usecases.category;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class CategoryInputRequestValidator extends Validator<Category> {
    @Override
    public Notification validate(Category category) {
        Notification notification = new Notification();

        if (category == null){
            notification.addError("Category is null");
            return notification;
        }
        if(nullOrEmpty(category.getName()))
            notification.addError("Name is null or empty");
        if(nullOrEmpty(category.getArea()))
            notification.addError("Area is null or empty");
        if(nullOrEmpty(category.getApplication()))
            notification.addError("Application is null or empty");

        return notification;
    }
}
