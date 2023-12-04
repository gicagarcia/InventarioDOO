package br.edu.ifsp.inventariodoo.application.view.domain.usecases.place;


import br.edu.ifsp.inventariodoo.application.repository.inmemory.InMemoryPlaceDAO;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.usecases.place.CreatePlaceUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.place.DeletePlaceUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.place.PlaceDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeletePlaceUseCaseTest {
    private DeletePlaceUseCase deletePlaceUseCase;
    private PlaceDAO placeDAO;
    private CreatePlaceUseCase createPlaceUseCase;

    @BeforeEach
    void config(){
        PlaceDAO placeDAO = new InMemoryPlaceDAO();
        deletePlaceUseCase = new DeletePlaceUseCase(placeDAO);
        createPlaceUseCase = new CreatePlaceUseCase(placeDAO);
    }
    @Test
    void DeleteTrue(){
        Place place = new Place(1,15,"Bloco A");
        createPlaceUseCase.insert(place);
        assertTrue(deletePlaceUseCase.delete(place));
    }
    @Test
    void DeleteTrueid(){
        Place place = new Place(1,15,"Bloco A");
        createPlaceUseCase.insert(place);
        assertTrue(deletePlaceUseCase.delete(place.getId()));
    }

    @Test
    void DeleteFalse(){
        Place place = new Place();
        assertThrows(EntityNotFoundException.class, () -> deletePlaceUseCase.delete(place));
    }
    @Test
    void DeleteFalseid(){
        Place place = new Place();
        assertThrows(EntityNotFoundException.class, () -> deletePlaceUseCase.delete(place.getId()));
    }

}