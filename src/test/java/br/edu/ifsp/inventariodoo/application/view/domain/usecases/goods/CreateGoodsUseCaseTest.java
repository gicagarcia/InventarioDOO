package br.edu.ifsp.inventariodoo.application.view.domain.usecases.goods;

import br.edu.ifsp.inventariodoo.application.repository.InMemoryGoodsDAO;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.CreateGoodsUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.GoodsDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateGoodsUseCaseTest {
    private CreateGoodsUseCase createGoodsUseCase;
    private GoodsDAO goodsDAO;

    @BeforeEach
    void config(){
        GoodsDAO goodsDAO = new InMemoryGoodsDAO();
        createGoodsUseCase = new CreateGoodsUseCase(goodsDAO);
    }

    @Test
    void CreateValidGoods(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        assertNotNull(createGoodsUseCase.insert(goods1));
    }

    @Test
    void CreateGoodsWithoutName(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods("","escola","i3 processador" ,category1);
        assertThrows(IllegalArgumentException.class, () -> createGoodsUseCase.insert(goods1));
    }

    @Test
    void CreateGoodsWithoutOrigin(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods("notebook","","i3 processador" ,category1);
        assertThrows(IllegalArgumentException.class, () -> createGoodsUseCase.insert(goods1));
    }

    @Test
    void CreateGoodsWithoutCharacteristics(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods("notebook","escola","" ,category1);
        assertThrows(IllegalArgumentException.class, () -> createGoodsUseCase.insert(goods1));
    }

}