package br.edu.ifsp.inventariodoo.domain.usecases.person;

import br.edu.ifsp.inventariodoo.domain.entities.user.Place;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class PersonInputRequestValidator extends Validator<Place>{
    @Override
    public Notification validate(Place place){
        Notification notification = new Notification();
        if(place == null){
            notification.addError("Person is null");
            return notification;
        }
        if(nullOrEmpty(place.getRegistrationId()))
            notification.addError("Registration ID is null or empty");
        if(nullOrEmpty(place.getName()))
            notification.addError("Name is null or empty");
        if(nullOrEmpty(place.getEmail()))
            notification.addError("E-mail is null or empty");
        if(nullOrEmpty(place.getPhone()))
            notification.addError("Phone number is null or empty");

        return notification;
    }
}
