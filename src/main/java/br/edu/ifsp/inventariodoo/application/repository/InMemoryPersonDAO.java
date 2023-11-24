package br.edu.ifsp.inventariodoo.application.repository;

import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.person.PersonDAO;

import java.util.*;

public class InMemoryPersonDAO implements PersonDAO {

    private static final Map<String, Person> db = new LinkedHashMap<>();

    @Override
    public String create(Person person) {
        db.put(person.getRegistrationId(), person);
        return person.getRegistrationId();
    }

    @Override
    public Optional<Person> findOne(String registration) {
        return db.values().stream()
                .filter(person -> person.getRegistrationId().equals(registration))
                .findAny();
   }

    @Override
    public Optional<Person> findByEmail(String email){
        return db.values().stream()
                .filter(person -> person.getEmail().equals(email))
                .findAny();
    }

    @Override
    public List<Person> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Person person) {
        String registration = person.getRegistrationId();
        if (db.containsKey(registration)) {
            db.replace(registration, person);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        if(db.containsKey(key)){
            db.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Person person) {
        return deleteByKey(person.getRegistrationId());
    }
}
