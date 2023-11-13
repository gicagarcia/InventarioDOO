package br.edu.ifsp.inventariodoo.domain.usecases.place;

import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class PlaceInputRequestValidator extends Validator<Place>{
    @Override
    public Notification validate(Place place){
        Notification notification = new Notification();
        if(place == null){
            notification.addError("Place is null");
            return notification;
        }
        if(nullOrEmpty(place.getBlock()))
            notification.addError("Block is null or empty");
        if(place.getNumber() == null)
            notification.addError("Number is null");

        return notification;
    }
}
