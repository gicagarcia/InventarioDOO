package br.edu.ifsp.inventariodoo.domain.usecases.person;

import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.DAO;

import java.util.Optional;

public interface PersonDAO extends DAO<Person, String> {
    Optional<Person> findByRegistration(String registration);
}
