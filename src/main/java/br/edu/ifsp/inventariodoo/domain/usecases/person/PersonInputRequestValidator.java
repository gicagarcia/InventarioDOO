package br.edu.ifsp.inventariodoo.domain.usecases.person;

import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.entities.user.TypeWorker;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class PersonInputRequestValidator extends Validator<Person>{
    @Override
    public Notification validate(Person person){
        Notification notification = new Notification();
        if(person == null){
            notification.addError("Person is null");
            return notification;
        }
        if(nullOrEmpty(person.getRegistrationId()))
            notification.addError("Registration ID is null or empty");
        if(nullOrEmpty(person.getName()))
            notification.addError("Name is null or empty");
        if(nullOrEmpty(person.getEmail()))
            notification.addError("E-mail is null or empty");
        if(nullOrEmpty(person.getPhone()))
            notification.addError("Phone number is null or empty");

        if(person.hasRole(TypeWorker.WAREHOUSEMAN) || person.hasRole(TypeWorker.PREMIER)){
            if(nullOrEmpty(person.getPassword())){
                notification.addError("Password is null or empty");}
        }

        return notification;
    }
}
