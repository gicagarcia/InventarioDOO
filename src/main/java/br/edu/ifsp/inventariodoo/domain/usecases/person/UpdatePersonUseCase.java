package br.edu.ifsp.inventariodoo.domain.usecases.person;

import br.edu.ifsp.inventariodoo.domain.entities.user.Place;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class UpdatePersonUseCase {
    private PersonDAO personDAO;

    public UpdatePersonUseCase(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public boolean update(Place place){
        Validator<Place> validator = new PersonInputRequestValidator();
        Notification notification = validator.validate(place);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String registrationId = place.getRegistrationId();
        if(personDAO.findByRegistration(registrationId).isEmpty())
            throw new EntityNotFoundException("Registration ID not found.");

        return personDAO.update(place);
    }
}
