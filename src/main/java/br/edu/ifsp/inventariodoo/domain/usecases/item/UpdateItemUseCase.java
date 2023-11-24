package br.edu.ifsp.inventariodoo.domain.usecases.item;

import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class UpdateItemUseCase {
    private ItemDAO itemDAO;

    public UpdateItemUseCase(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public boolean update(Item item){
        Validator<Item> validator = new ItemInputRequestValidator();
        Notification notification = validator.validate(item);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String tag = item.getTag();
        if(itemDAO.findOne(tag).isEmpty())
            throw new EntityNotFoundException("Tag not found.");

        return itemDAO.update(item);
    }
}
