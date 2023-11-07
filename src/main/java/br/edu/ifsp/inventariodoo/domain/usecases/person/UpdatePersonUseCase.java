package br.edu.ifsp.inventariodoo.domain.usecases.person;

import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class UpdatePersonUseCase {
    private PersonDAO personDAO;

    public UpdatePersonUseCase(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public boolean update(Person person){
        Validator<Person> validator = new PersonInputRequestValidator();
        Notification notification = validator.validate(person);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String registrationId = person.getRegistrationId();
        if(personDAO.findByRegistration(registrationId).isEmpty())
            throw new EntityNotFoundException("Registration ID not found.");

        return personDAO.update(person);
    }
}
