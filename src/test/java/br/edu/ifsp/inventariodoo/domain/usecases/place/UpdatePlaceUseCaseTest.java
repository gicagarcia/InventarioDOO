package br.edu.ifsp.inventariodoo.domain.usecases.place;

import br.edu.ifsp.inventariodoo.application.repository.InMemoryGoodsDAO;
import br.edu.ifsp.inventariodoo.application.repository.InMemoryPlaceDAO;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.CreateGoodsUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.GoodsDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.UpdateGoodsUseCase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdatePlaceUseCaseTest {
    private UpdatePlaceUseCase updatePlaceUseCase;
    private PlaceDAO placeDAO;
    private CreatePlaceUseCase createPlaceUseCase;

    @BeforeEach
    void config(){
        PlaceDAO placeDAO = new InMemoryPlaceDAO();
        updatePlaceUseCase = new UpdatePlaceUseCase(placeDAO);
        createPlaceUseCase = new CreatePlaceUseCase(placeDAO);
    }

    @Test
    void UpdateTrue(){
        Place place1 = new Place(65,"bloco 1");
        createPlaceUseCase.insert(place1);
        place1.setBlock("bloco 2");
        assertTrue(updatePlaceUseCase.update(place1));
    }

    @Test
    void UpdateFalseApplication(){
        Place place1 = new Place(65,"bloco 1");
        createPlaceUseCase.insert(place1);
        place1.setBlock("");
        assertThrows(IllegalArgumentException.class, () -> updatePlaceUseCase.update(place1));
    }

}