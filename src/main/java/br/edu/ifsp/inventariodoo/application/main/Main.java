package br.edu.ifsp.inventariodoo.application.main;

import br.edu.ifsp.inventariodoo.application.repository.InMemoryPersonDAO;
import br.edu.ifsp.inventariodoo.application.repository.InMemoryPlaceDAO;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.entities.user.SecretPhrase;
import br.edu.ifsp.inventariodoo.domain.usecases.person.*;
import br.edu.ifsp.inventariodoo.domain.usecases.place.*;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static CreatePersonUseCase createPersonUseCase;
    private static UpdatePersonUseCase updatePersonUseCase;
    private static DeletePersonUseCase deletePersonUseCase;
    private static FindPersonUseCase findPersonUseCase;

    private static CreatePlaceUseCase createPlaceUseCase;
    private static UpdatePlaceUseCase updatePlaceUseCase;
    private static DeletePlaceUseCase deletePlaceUseCase;
    private static FindPlaceUseCase findPlaceUseCase;


    public static void main(String[] args){
        configureInjection();

        //Person
        Person person = Person.asPerson("123", "Gi", "gi@gmail.com", "123");
        createPersonUseCase.insert(person);
        System.out.println(person.toString());

        //Warehouseman
        Person warehouseman = Person.asWarehouseman("234", "Paulo", "pa@gmail.com",
                "1234", "234");
        System.out.println(warehouseman.toString());
        //Secret phrase
        List<SecretPhrase> phrases = new ArrayList<>();
        warehouseman.setSecretPhrases(phrases);
        warehouseman.registerSecretPhrase("Nome da cachorra", "Babi");
        System.out.println(warehouseman.toString());

        //Change password
        String answer = "Babi";
        boolean ok = warehouseman.checkSecretPhrase(phrases.get(0), answer);
        System.out.println(ok);
        if(person.checkSecretPhrase(phrases.get(0), answer)){
            String newPassword = "544";
            warehouseman.setPassword(newPassword);
        }
        System.out.println(warehouseman.toString());

        //Premier
        Person premier = Person.asPremier("345", "Andreia", "andreia@gmail.com", "999",
                "ancora");
        System.out.println((premier.toString()));
        //Secret phrase
        List<SecretPhrase> phrases2 = new ArrayList<>();
        warehouseman.setSecretPhrases(phrases2);
        warehouseman.registerSecretPhrase("Nome da cachorra", "Monica");
        System.out.println(premier.toString());
        //Check and change
        String answer2 = "Pandora";
        boolean ok2 = premier.checkSecretPhrase(phrases.get(0), answer2);
        System.out.println(ok2);
        if(person.checkSecretPhrase(phrases.get(0), answer)){
            String newPassword = "799";
            warehouseman.setPassword(newPassword);
        }


        //Place
        Place place = new Place(1,538, "AT5");
        System.out.println(place.toString());
        createPlaceUseCase.insert(place);

    }


    private static void configureInjection() {
        PersonDAO personDAO = new InMemoryPersonDAO();
        createPersonUseCase = new CreatePersonUseCase(personDAO);
        updatePersonUseCase = new UpdatePersonUseCase(personDAO);
        deletePersonUseCase = new DeletePersonUseCase(personDAO);
        findPersonUseCase = new FindPersonUseCase(personDAO);

        PlaceDAO placeDAO = new InMemoryPlaceDAO();
        createPlaceUseCase = new CreatePlaceUseCase(placeDAO);
        updatePlaceUseCase = new UpdatePlaceUseCase(placeDAO);
        deletePlaceUseCase = new DeletePlaceUseCase(placeDAO);
        findPlaceUseCase = new FindPlaceUseCase(placeDAO);
    }
}
