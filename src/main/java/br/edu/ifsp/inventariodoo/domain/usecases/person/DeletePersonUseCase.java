package br.edu.ifsp.inventariodoo.domain.usecases.person;

import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;

public class DeletePersonUseCase {
    private PersonDAO personDAO;

    public DeletePersonUseCase(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public boolean delete(String registration){
        if(registration == null || personDAO.findByRegistration(registration).isEmpty()) //findOne quando ID
            throw new EntityNotFoundException("Person not found");
        return personDAO.deleteByKey(registration);
    }
    public boolean delete(Person person){
        if(person == null || personDAO.findByRegistration(person.getRegistrationId()).isEmpty())
            throw new EntityNotFoundException("Person not found");
        return personDAO.delete(person);
    }
}
