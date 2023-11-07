package br.edu.ifsp.inventariodoo.domain.usecases.goods;

import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class UpdateGoodsUseCase {
    private GoodsDAO goodsDAO;

    public UpdateGoodsUseCase(GoodsDAO goodsDAO) {
        this.goodsDAO = goodsDAO;
    }

    public boolean update(Goods goods){
        Validator<Goods> validator = new GoodsInputRequestValidator();
        Notification notification = validator.validate(goods);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer id = goods.getId();
        if(goodsDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Goods not found");

        return goodsDAO.update(goods);
    }
}
