package br.edu.ifsp.inventariodoo.domain.usecases.goods;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class GoodsInputRequestValidator extends Validator<Goods> {
    @Override
    public Notification validate(Goods goods) {
        Notification notification = new Notification();

        if (goods == null){
            notification.addError("Goods is null");
            return notification;
        }
        if(nullOrEmpty(goods.getName()))
            notification.addError("Name is null or empty");
        if(nullOrEmpty(goods.getOrigin()))
            notification.addError("Origin is null or empty");
        if(nullOrEmpty(goods.getCharacteristics()))
            notification.addError("Characteristics is null or empty");
        if(nullOrEmpty(goods.getCategory().getName()))
            notification.addError("Category's name of goods is null or empty");

        return notification;
    }
}
