package br.edu.ifsp.inventariodoo.domain.usecases.item;

import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.GoodsDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.GoodsInputRequestValidator;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class CreateItemUseCase {

    private ItemDAO itemDAO;

    public CreateItemUseCase(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public String insert(Item item){
        Validator<Item> validator = new ItemInputRequestValidator();
        Notification notification = validator.validate(item);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String tag = item.getTag();
        if(itemDAO.findByTag(tag).isPresent())
            throw new EntityAlreadyExistsException("This Tag is already in use");
        
        return itemDAO.create(item);
    }
}
