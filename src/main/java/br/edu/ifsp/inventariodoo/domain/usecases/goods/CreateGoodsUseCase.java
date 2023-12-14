package br.edu.ifsp.inventariodoo.domain.usecases.goods;

import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class CreateGoodsUseCase {

    private GoodsDAO goodsDAO;

    public CreateGoodsUseCase(GoodsDAO goodsDAO) {
        this.goodsDAO = goodsDAO;
    }

    public Integer insert(Goods goods){
        Validator<Goods> validator = new GoodsInputRequestValidator();
        Notification notification = validator.validate(goods);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

//        Integer id = goods.getId();
//        if(goodsDAO.findOne(id).isPresent())
//            throw new EntityAlreadyExistsException("This ID is already in use");
        
        return goodsDAO.create(goods);
    }
}
