package br.edu.ifsp.inventariodoo.domain.usecases.person;

import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class CreatePersonUseCase {
    private PersonDAO personDAO;

    public CreatePersonUseCase(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public String insert(Person person){
        Validator<Person> validator = new PersonInputRequestValidator();
        Notification notification = validator.validate(person);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        String registrationId = person.getRegistrationId();
        if(personDAO.findOne(registrationId).isPresent())
            throw new EntityAlreadyExistsException("This registration ID is already in use");

        return personDAO.create(person);
    }
}
