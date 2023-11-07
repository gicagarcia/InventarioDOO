package br.edu.ifsp.inventariodoo.domain.usecases.place;

import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindPlaceUseCase {
    private PlaceDAO placeDAO;

    public FindPlaceUseCase(PlaceDAO placeDAO) {
        this.placeDAO = placeDAO;
    }

    public Optional<Place> findOne(Integer id){
        if(id == null)
            throw new IllegalArgumentException("Place ID can not be null.");
        return placeDAO.findOne(id);
    }

    public List<Place> findAll(){
        return placeDAO.findAll();
    }
}
