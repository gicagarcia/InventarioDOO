package br.edu.ifsp.inventariodoo.domain.usecases.goods;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;

public class DeleteGoodsUseCase {

    private GoodsDAO goodsDAO;

    public DeleteGoodsUseCase(GoodsDAO goodsDAO) {
        this.goodsDAO = goodsDAO;
    }

    public boolean delete(Integer id){
        if(id == null || goodsDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Good not found");
        return goodsDAO.deleteByKey(id);

    }

    public boolean delete(Goods goods){
        if(goods == null || goodsDAO.findOne(goods.getId()).isEmpty())
            throw new EntityNotFoundException("Good not found");
        return goodsDAO.delete(goods);
    }
}
