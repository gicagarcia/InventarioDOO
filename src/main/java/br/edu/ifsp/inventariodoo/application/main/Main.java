package br.edu.ifsp.inventariodoo.application.main;

import br.edu.ifsp.inventariodoo.application.repository.InMemoryPersonDAO;
import br.edu.ifsp.inventariodoo.application.repository.InMemoryPlaceDAO;
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
    }

    List<SecretPhrase> secret = new ArrayList<>();
    SecretPhrase phrase = new SecretPhrase("nome da sua cachorra", "babi");
    Person person = new Person().asWarehouseman("123", "Gi", "gi@gmail.com", null, "123", );

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
