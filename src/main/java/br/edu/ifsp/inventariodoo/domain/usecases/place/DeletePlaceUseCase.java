package br.edu.ifsp.inventariodoo.domain.usecases.place;

import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;

public class DeletePlaceUseCase {
    private PlaceDAO placeDAO;

    public DeletePlaceUseCase(PlaceDAO placeDAO) {
        this.placeDAO = placeDAO;
    }

    public boolean delete(Integer id){
        if(id == null || placeDAO.findOne(id).isEmpty()) //findOne quando ID
            throw new EntityNotFoundException("Place not found");
        return placeDAO.deleteByKey(id);
    }

}
