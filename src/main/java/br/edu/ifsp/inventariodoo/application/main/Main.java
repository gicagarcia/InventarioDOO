package br.edu.ifsp.inventariodoo.application.main;

import br.edu.ifsp.inventariodoo.application.repository.InMemoryPersonDAO;
import br.edu.ifsp.inventariodoo.application.repository.InMemoryPlaceDAO;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.entities.user.SecretPhrase;
import br.edu.ifsp.inventariodoo.domain.entities.user.TypeWorker;
import br.edu.ifsp.inventariodoo.domain.usecases.category.*;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.*;
import br.edu.ifsp.inventariodoo.domain.usecases.item.*;
import br.edu.ifsp.inventariodoo.application.repository.InMemoryPersonDAO;
import br.edu.ifsp.inventariodoo.application.repository.InMemoryPlaceDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.person.*;
import br.edu.ifsp.inventariodoo.domain.usecases.place.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Main {

    private static CreatePersonUseCase createPersonUseCase;
    private static UpdatePersonUseCase updatePersonUseCase;
    private static DeletePersonUseCase deletePersonUseCase;
    private static FindPersonUseCase findPersonUseCase;

    private static CreatePlaceUseCase createPlaceUseCase;
    private static UpdatePlaceUseCase updatePlaceUseCase;
    private static DeletePlaceUseCase deletePlaceUseCase;
    private static FindPlaceUseCase findPlaceUseCase;

    private static CreateCategoryUseCase createCategoryUseCase;
    private static UpdateCategoryUseCase updateCategoryUseCase;
    private static DeleteCategoryUseCase deleteCategoryUseCase;
    private static FindCategoryUseCase findCategoryUseCase;

    private static CreateGoodsUseCase createGoodsUseCase;
    private static UpdateGoodsUseCase updateGoodsUseCase;
    private static DeleteGoodsUseCase deleteGoodsUseCase;
    private static FindGoodsUseCase findGoodsUseCase;

    private static CreateItemUseCase createItemUseCase;
    private static UpdateItemUseCase updateItemUseCase;
    private static DeleteItemUseCase deleteItemUseCase;
    private static FindItemUseCase findItemUseCase;


    public static void main(String[] args){
        configureInjection();


        Category category1 = new Category(1, "joao", "centro", "aplicacao");
        createCategoryUseCase.insert(category1);
        System.out.println("Category: " + category1 + "\n");



        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        createGoodsUseCase.insert(goods1);
        System.out.println("antes de atualizar: " + goods1+"\n");


        Optional<Goods> foundGoods1 = findGoodsUseCase.findOne(goods1.getId());
        if (foundGoods1.isPresent()) {
            System.out.println("Inserção de goods1 bem-sucedida\n");
        } else {
            System.out.println("Falha na inserção de goods1\n");
        }

        Place place1 = new Place(2,65,"bloco 1");
        createPlaceUseCase.insert(place1);
        System.out.println("print place 1:" + place1 +"\n");

        List<SecretPhrase> secret = new ArrayList<>();
        SecretPhrase phrase = new SecretPhrase("nome da sua cachorra", "babi");

        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        person1.addRole(TypeWorker.PERSON);
        Item item1 = new Item("1","computador","em aberto",goods1,person1,place1);

        createItemUseCase.insert(item1);
        System.out.println("antes de atualizar: " + item1+"\n");
        item1.setDescription("notebook");
        updateItemUseCase.update(item1);
        System.out.println("depois de att o item: " + item1 + "\n");


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

        CategoryDAO categoryDAO = new InMemoryCategoryDAO();
        createCategoryUseCase = new CreateCategoryUseCase(categoryDAO);
        updateCategoryUseCase = new UpdateCategoryUseCase(categoryDAO);
        deleteCategoryUseCase = new DeleteCategoryUseCase(categoryDAO);
        findCategoryUseCase = new FindCategoryUseCase(categoryDAO);

        GoodsDAO goodsDAO = new InMemoryGoodsDAO();
        createGoodsUseCase = new CreateGoodsUseCase(goodsDAO);
        updateGoodsUseCase = new UpdateGoodsUseCase(goodsDAO);
        deleteGoodsUseCase = new DeleteGoodsUseCase(goodsDAO);
        findGoodsUseCase = new FindGoodsUseCase(goodsDAO);

        ItemDAO itemDAO = new InMemoryItemDAO();
        createItemUseCase = new CreateItemUseCase(itemDAO);
        updateItemUseCase = new UpdateItemUseCase(itemDAO);
        deleteItemUseCase = new DeleteItemUseCase(itemDAO);
        findItemUseCase = new FindItemUseCase(itemDAO);


    }
}
