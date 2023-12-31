package br.edu.ifsp.inventariodoo.application.view.domain.usecases.place;

import br.edu.ifsp.inventariodoo.application.repository.inmemory.InMemoryPlaceDAO;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.usecases.place.CreatePlaceUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.place.PlaceDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreatePlaceUseCaseTest {
    private CreatePlaceUseCase createPlaceUseCase;
    private PlaceDAO placeDAO;

    @BeforeEach
    void config(){
        PlaceDAO placeDAO = new InMemoryPlaceDAO();
        createPlaceUseCase = new CreatePlaceUseCase(placeDAO);
    }

    @Test
    void CreateValidPlace(){
        Place place1 = new Place(65,"bloco 1");
        assertNotNull(createPlaceUseCase.insert(place1));
    }

    @Test
    void CreatePlaceWithoutNumber(){
        Place place1 = new Place(null,"bloco 1");
        assertThrows(IllegalArgumentException.class, () -> createPlaceUseCase.insert(place1));
    }

    @Test
    void CreatePlaceWithoutBlock(){
        Place place1 = new Place(65,"");
        assertThrows(IllegalArgumentException.class, () -> createPlaceUseCase.insert(place1));
    }

}