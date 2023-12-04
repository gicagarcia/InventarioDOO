package br.edu.ifsp.inventariodoo.application.view.domain.usecases.goods;

import br.edu.ifsp.inventariodoo.application.repository.inmemory.InMemoryGoodsDAO;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.CreateGoodsUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.DeleteGoodsUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.GoodsDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteGoodsUseCaseTest {
    private DeleteGoodsUseCase deleteGoodsUseCase;
    private GoodsDAO goodsDAO;
    private CreateGoodsUseCase createGoodsUseCase;

    @BeforeEach
    void config(){
        GoodsDAO goodsDAO = new InMemoryGoodsDAO();
        deleteGoodsUseCase = new DeleteGoodsUseCase(goodsDAO);
        createGoodsUseCase = new CreateGoodsUseCase(goodsDAO);
    }
    @Test
    void DeleteTrue(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        createGoodsUseCase.insert(goods1);
        assertTrue(deleteGoodsUseCase.delete(goods1));
    }
    @Test
    void DeleteTrueid(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        createGoodsUseCase.insert(goods1);
        assertTrue(deleteGoodsUseCase.delete(goods1.getId()));
    }

    @Test
    void DeleteFalse(){
        Goods goods = new Goods();
        assertThrows(EntityNotFoundException.class, () -> deleteGoodsUseCase.delete(goods));
    }
    @Test
    void DeleteFalseid(){
        Goods goods = new Goods();
        assertThrows(EntityNotFoundException.class, () -> deleteGoodsUseCase.delete(goods.getId()));
    }

}