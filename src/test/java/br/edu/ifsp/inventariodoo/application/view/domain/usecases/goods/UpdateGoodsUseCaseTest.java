package br.edu.ifsp.inventariodoo.application.view.domain.usecases.goods;

import br.edu.ifsp.inventariodoo.application.repository.inmemory.InMemoryGoodsDAO;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.CreateGoodsUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.GoodsDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.UpdateGoodsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateGoodsUseCaseTest {
    private UpdateGoodsUseCase updateGoodsUseCase;
    private GoodsDAO goodsDAO;
    private CreateGoodsUseCase createGoodsUseCase;

    @BeforeEach
    void config(){
        GoodsDAO goodsDAO = new InMemoryGoodsDAO();
        updateGoodsUseCase = new UpdateGoodsUseCase(goodsDAO);
        createGoodsUseCase = new CreateGoodsUseCase(goodsDAO);
    }

    @Test
    void UpdateTrue(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods("notebook","escola","i3 processador" ,category1);
        createGoodsUseCase.insert(goods1);
        goods1.setName("máquinas");
        assertTrue(updateGoodsUseCase.update(goods1));
    }

    @Test
    void UpdateFalseApplication(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods("notebook","escola","i3 processador" ,category1);
        createGoodsUseCase.insert(goods1);
        goods1.setName("");
        assertThrows(IllegalArgumentException.class, () -> updateGoodsUseCase.update(goods1));
    }

}