package br.edu.ifsp.inventariodoo.domain.usecases.person;

import br.edu.ifsp.inventariodoo.domain.entities.user.Place;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindPersonUseCase {
    private PersonDAO personDAO;

    public FindPersonUseCase(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }
    //public Optional<Person> findOne(String registation){
    //    if(registation == null)
    //        throw new IllegalArgumentException("Registration ID can not be null.");
    //    return personDAO.findOne(registation);
    //}

    public Optional<Place> findOneByRegistration(String registration){
        if(Validator.nullOrEmpty(registration))
            throw new IllegalArgumentException("Registration ID can not be null or empty.");
        return personDAO.findByRegistration(registration);
    }

    public List<Place> findAll(){
        return personDAO.findAll();
    }
}
