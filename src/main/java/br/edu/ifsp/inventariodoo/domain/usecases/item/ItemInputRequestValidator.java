package br.edu.ifsp.inventariodoo.domain.usecases.item;

import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class ItemInputRequestValidator extends Validator<Item> {
    @Override
    public Notification validate(Item item) {
        Notification notification = new Notification();

        if (item == null){
            notification.addError("Item is null");
            return notification;
        }

        if(nullOrEmpty(item.getTag()))
            notification.addError("Tag is null or empty");
        if(nullOrEmpty(item.getDescription()))
            notification.addError("Description is null or empty");

        if(item.getStatus() == null)
            notification.addError("Status is null");
        if(item.getGoods() == null)
            notification.addError("Goods of item is null");
        if(item.getResponsible() == null)
            notification.addError("Responsible of item is null");
        if(item.getPlace()== null)
            notification.addError("Place of item is null");

        return notification;
    }
}
