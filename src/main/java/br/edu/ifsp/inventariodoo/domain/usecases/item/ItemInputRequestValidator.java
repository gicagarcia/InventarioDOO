package br.edu.ifsp.inventariodoo.domain.usecases.item;

import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
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
        if(nullOrEmpty(item.getDescription()))
            notification.addError("Description is null or empty");
        if(nullOrEmpty(item.getStatus()))
            notification.addError("Status is null or empty");
        if(nullOrEmpty(item.getGoods().getName()))
            notification.addError("Goods of item is null or empty");
        //if(nullOrEmpty(item.getResponsible())) //campo pra verificar responsavel, arrumar depois
            //notification.addError("Category's name of goods is null or empty");
        if(nullOrEmpty(item.getPlace().getBlock()))
            notification.addError("Place of item is null or empty");

        return notification;
    }
}
