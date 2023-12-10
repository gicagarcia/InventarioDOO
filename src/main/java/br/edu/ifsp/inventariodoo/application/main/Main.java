package br.edu.ifsp.inventariodoo.application.main;

import br.edu.ifsp.inventariodoo.application.repository.inmemory.*;
import br.edu.ifsp.inventariodoo.application.repository.sqlite.*;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.category.*;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.*;
import br.edu.ifsp.inventariodoo.domain.usecases.inventory.CreateInventoryUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.inventory.FindInventoryUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.inventory.InventoryDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.item.*;
import br.edu.ifsp.inventariodoo.domain.usecases.person.*;
import br.edu.ifsp.inventariodoo.domain.usecases.place.*;
import br.edu.ifsp.inventariodoo.domain.usecases.register.FindRegisterUseCase;
import javafx.stage.Window;

public class Main {

    public static CreatePersonUseCase createPersonUseCase;
    public static DeletePersonUseCase deletePersonUseCase;
    public static FindPersonUseCase findPersonUseCase;
    public static UpdatePersonUseCase updatePersonUseCase;

    public static CreateItemUseCase createItemUseCase;
    public static DeleteItemUseCase deleteItemUseCase;
    public static FindItemUseCase findItemUseCase;
    public static UpdateItemUseCase updateItemUseCase;

    public static CreatePlaceUseCase createPlaceUseCase;
    public static DeletePlaceUseCase deletePlaceUseCase;
    public static FindPlaceUseCase findPlaceUseCase;
    public static UpdatePlaceUseCase updatePlaceUseCase;

    public static CreateGoodsUseCase createGoodsUseCase;
    public static DeleteGoodsUseCase deleteGoodsUseCase;
    public static FindGoodsUseCase findGoodsUseCase;
    public static UpdateGoodsUseCase updateGoodsUseCase;

    public static CreateCategoryUseCase createCategoryUseCase;
    public static DeleteCategoryUseCase deleteCategoryUseCase;
    public static FindCategoryUseCase findCategoryUseCase;
    public static UpdateCategoryUseCase updateCategoryUseCase;

    public static CreateInventoryUseCase createInventoryUseCase;
    public static FindInventoryUseCase findInventoryUseCase;

    public static FindRegisterUseCase findRegisterUseCase;

    public static void main(String[] args) {
        configureInjection();
        setupDatabase();
        populateDataBase();

    }

    private static void populateDataBase() {
        //Person person = Person.asPerson();
        //createPersonUseCase.insert(person);
    }

    private static void setupDatabase() {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }

    private static void configureInjection() {
        PersonDAO personDAO = new SqlitePersonDAO();
        createPersonUseCase = new CreatePersonUseCase(personDAO);
        updatePersonUseCase = new UpdatePersonUseCase(personDAO);
        findPersonUseCase = new FindPersonUseCase(personDAO);
        deletePersonUseCase = new DeletePersonUseCase(personDAO);

        ItemDAO itemDAO = new SqliteItemDAO();
        createItemUseCase = new CreateItemUseCase(itemDAO);
        updateItemUseCase = new UpdateItemUseCase(itemDAO);
        findItemUseCase = new FindItemUseCase(itemDAO);
        deleteItemUseCase = new DeleteItemUseCase(itemDAO);

        PlaceDAO placeDAO = new SqlitePlaceDAO();
        createPlaceUseCase = new CreatePlaceUseCase(placeDAO);
        updatePlaceUseCase = new UpdatePlaceUseCase(placeDAO);
        findPlaceUseCase = new FindPlaceUseCase(placeDAO);
        deletePlaceUseCase = new DeletePlaceUseCase(placeDAO);

        GoodsDAO goodsDAO = new SqliteGoodsDAO();
        createGoodsUseCase = new CreateGoodsUseCase(goodsDAO);
        updateGoodsUseCase = new UpdateGoodsUseCase(goodsDAO);
        findGoodsUseCase = new FindGoodsUseCase(goodsDAO);
        deleteGoodsUseCase = new DeleteGoodsUseCase(goodsDAO);

        CategoryDAO categoryDAO = new SqliteCategoryDAO();
        createCategoryUseCase = new CreateCategoryUseCase(categoryDAO);
        updateCategoryUseCase = new UpdateCategoryUseCase(categoryDAO);
        findCategoryUseCase = new FindCategoryUseCase(categoryDAO);
        deleteCategoryUseCase = new DeleteCategoryUseCase(categoryDAO);

        InventoryDAO inventoryDAO = new SqliteInventoryDAO();
        createInventoryUseCase = new CreateInventoryUseCase(inventoryDAO);
    }
}
