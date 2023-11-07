package br.edu.ifsp.inventariodoo.domain.usecases.place;

import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class CreatePlaceUseCase {
    private PlaceDAO placeDAO;

    public CreatePlaceUseCase(PlaceDAO placeDAO) {
        this.placeDAO = placeDAO;
    }

    public Integer insert(Place place){
        Validator<Place> validator = new PlaceInputRequestValidator();
        Notification notification = validator.validate(place);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer id = place.getId();
        if(placeDAO.findOne(id).isPresent())
            throw new EntityAlreadyExistsException("This ID is already in use");

        return placeDAO.create(place);
    }
}
