package br.edu.ifsp.inventariodoo.domain.usecases.person;

import br.edu.ifsp.inventariodoo.domain.entities.user.Place;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class CreatePersonUseCase {
    private PersonDAO personDAO;

    public CreatePersonUseCase(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public String insert(Place place){
        Validator<Place> validator = new PersonInputRequestValidator();
        Notification notification = validator.validate(place);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String registrationId = place.getRegistrationId();
        if(personDAO.findByRegistration(registrationId).isPresent())
            throw new EntityAlreadyExistsException("This registration ID is already in use");

        return personDAO.create(place);
    }
}
