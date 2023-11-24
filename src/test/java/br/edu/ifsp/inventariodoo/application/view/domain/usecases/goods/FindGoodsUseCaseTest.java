package br.edu.ifsp.inventariodoo.application.view.domain.usecases.goods;


import br.edu.ifsp.inventariodoo.application.repository.InMemoryGoodsDAO;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.CreateGoodsUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.FindGoodsUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.GoodsDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindGoodsUseCaseTest {
    private FindGoodsUseCase findGoodsUseCase;
    private GoodsDAO goodsDAO;
    private CreateGoodsUseCase createGoodsUseCase;
    @BeforeEach
    void config(){
        GoodsDAO goodsDAO = new InMemoryGoodsDAO();
        findGoodsUseCase = new FindGoodsUseCase(goodsDAO);
        createGoodsUseCase = new CreateGoodsUseCase(goodsDAO);
    }
    @Test
    void FindValidGoods(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        assertNotNull(findGoodsUseCase.findOne(goods1.getId()));
    }

    @Test
    void FindValidGoodsByName(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        assertNotNull(findGoodsUseCase.findOneByName(goods1.getName()));
    }

    @Test
    void FindInvalidGoods(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(null,"notebook","escola","i3 processador" ,category1);
        assertThrows(IllegalArgumentException.class, () -> findGoodsUseCase.findOne(goods1.getId()));
    }
    @Test
    void FindInvalidCategoryByName(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods();
        assertThrows(IllegalArgumentException.class, () -> findGoodsUseCase.findOneByName(goods1.getName()));
    }


}