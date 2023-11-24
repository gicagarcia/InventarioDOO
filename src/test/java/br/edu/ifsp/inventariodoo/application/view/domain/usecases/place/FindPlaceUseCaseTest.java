package br.edu.ifsp.inventariodoo.application.view.domain.usecases.place;


import br.edu.ifsp.inventariodoo.application.repository.InMemoryPlaceDAO;

import br.edu.ifsp.inventariodoo.domain.entities.item.Place;

import br.edu.ifsp.inventariodoo.domain.usecases.place.FindPlaceUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.place.PlaceDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindPlaceUseCaseTest {
    private FindPlaceUseCase findPlaceUseCase;
    private PlaceDAO placeDAO;

    @BeforeEach
    void config(){
        PlaceDAO placeDAO = new InMemoryPlaceDAO();
        findPlaceUseCase = new FindPlaceUseCase(placeDAO);

    }
    @Test
    void FindValidPlace(){
        Place place1 = new Place(1,65,"bloco 1");
        assertNotNull(findPlaceUseCase.findOne(place1.getId()));
    }

    @Test
    void FindInvalidGoods(){
        Place place1 = new Place();
        assertThrows(IllegalArgumentException.class, () -> findPlaceUseCase.findOne(place1.getId()));
    }


}