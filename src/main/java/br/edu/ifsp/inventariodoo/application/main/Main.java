package br.edu.ifsp.inventariodoo.application.main;

import br.edu.ifsp.inventariodoo.application.repository.*;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.category.*;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.*;
import br.edu.ifsp.inventariodoo.domain.usecases.item.*;
import br.edu.ifsp.inventariodoo.domain.usecases.person.*;
import br.edu.ifsp.inventariodoo.domain.usecases.place.*;

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
