package br.edu.ifsp.inventariodoo.domain.usecases.person;

import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindPersonUseCase {
    private static PersonDAO personDAO;

    public FindPersonUseCase(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    public Optional<Person> findOne(String registration){
        if(Validator.nullOrEmpty(registration))
                throw new IllegalArgumentException("Registration ID can not be null or empty.");
            return personDAO.findOne(registration);
    }

    public Optional<Person> findOneByEmail(String email){
        if (Validator.nullOrEmpty(email))
            throw new IllegalArgumentException("E-mail can not be null or empty.");
        return personDAO.findByEmail(email);
    }

    //public Optional<Person> findOneByRegistration(String registration){
       // if(Validator.nullOrEmpty(registration))
         //   throw new IllegalArgumentException("Registration ID can not be null or empty.");
        //return personDAO.findByRegistration(registration);
    //}

    public static List<Person> findAll(){//retirei static tbm
        return personDAO.findAll();
    }
}
