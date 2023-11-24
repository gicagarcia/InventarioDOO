package br.edu.ifsp.inventariodoo.domain.usecases.goods;

import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindGoodsUseCase {
    private GoodsDAO goodsDAO;

    public FindGoodsUseCase(GoodsDAO goodsDAO) {
        this.goodsDAO = goodsDAO;
    }

    public Optional<Goods> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null");
        return goodsDAO.findOne(id);
    }

    public Optional<Goods> findOneByName(String name){ //findOneByName mesmo nome de category nao sei se da problema
        if(Validator.nullOrEmpty(name))
            throw new IllegalArgumentException("Name can not be null or empty");
        return goodsDAO.findByName(name);
    }
    public List<Goods> findAll(){
        return goodsDAO.findAll();
    }
}
