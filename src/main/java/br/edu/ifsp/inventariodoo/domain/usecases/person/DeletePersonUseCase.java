package br.edu.ifsp.inventariodoo.domain.usecases.person;

import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;

public class DeletePersonUseCase {
    private PersonDAO personDAO;

    public DeletePersonUseCase(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public boolean delete(String registration){
        if(registration == null || personDAO.findOne(registration).isEmpty()) //findOne quando ID
            throw new EntityNotFoundException("Person not found");
        return personDAO.deleteByKey(registration);
    }

    public boolean deleteByEmail(String email){
        if(email == null || personDAO.findByEmail(email).isEmpty())
            throw new EntityNotFoundException("Person not found");
        return personDAO.deleteByKey(email);
    }

    public boolean delete(Person person){
        if(person == null || personDAO.findOne(person.getRegistrationId()).isEmpty())
            throw new EntityNotFoundException("Person not found");
        return personDAO.delete(person);
    }
}
