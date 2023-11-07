package br.edu.ifsp.inventariodoo.domain.usecases.place;

import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class UpdatePlaceUseCase {
    private PlaceDAO placeDAO;

    public UpdatePlaceUseCase(PlaceDAO placeDAO) {
        this.placeDAO = placeDAO;
    }

    public boolean update(Place place){
        Validator<Place> validator = new PlaceInputRequestValidator();
        Notification notification = validator.validate(place);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer id = place.getId();
        if(placeDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Place ID not found.");

        return placeDAO.update(place);
    }
}
