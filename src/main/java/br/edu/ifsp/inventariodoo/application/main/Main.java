package br.edu.ifsp.inventariodoo.application.main;


import br.edu.ifsp.inventariodoo.application.controller.MainUIView;
import br.edu.ifsp.inventariodoo.application.controller.WarehousemanUIView;
import br.edu.ifsp.inventariodoo.application.repository.inmemory.*;
import br.edu.ifsp.inventariodoo.application.repository.sqlite.*;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.category.*;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.*;
import br.edu.ifsp.inventariodoo.domain.usecases.inventory.CreateInventoryUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.inventory.FindInventoryUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.inventory.InventoryDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.item.*;
import br.edu.ifsp.inventariodoo.domain.usecases.person.*;
import br.edu.ifsp.inventariodoo.domain.usecases.place.*;
import br.edu.ifsp.inventariodoo.domain.usecases.register.CreateRegisterUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.register.FindRegisterUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.register.RegisterDAO;
import javafx.scene.control.Alert;


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
    public static CreateRegisterUseCase createRegisterUseCase;

    public static void main(String[] args) throws Exception {
        configureInjection();

//        setupDatabase();
        populateInMemory();
        try {
            MainUIView.main(args);
        }catch (IllegalArgumentException e){
            System.out.println(e);
        }

    }

    private static void populateInMemory() {
        Person warehouseman = Person.asWarehouseman("SC302959X", "Giovana", "gica@gmail.com", "16997608225", "123");
        warehouseman.registerSecretPhrase("Primeiro pet", "babi");
        createPersonUseCase.insert(warehouseman);

        Person president = Person.asPremier("SC330099", "Guilherme", "gui@gmail.com", "1699876022", "123");
        createPersonUseCase.insert(president);

        Category category = new Category("Eletrônicos", "Computação", "Engenharia da computação");
        createCategoryUseCase.insert(category);

        Place place = new Place(1, "Almoxarifado");
        createPlaceUseCase.insert(place);

        Goods goods = new Goods("Computador", "Dep. ADS", "i7", category);
        createGoodsUseCase.insert(goods);

        Item item = new Item("SL22", "Lenovo", StatusItem.NEW, goods, warehouseman, place);
        createItemUseCase.insert(item);

        Register register = new Register(place, item, warehouseman, "teste", StatusItem.NEW);
        createRegisterUseCase.insert(register);

        Inventory inventory = Inventory.withoutLists(warehouseman);
        inventory.addRegister(register);
        inventory.addInventor(warehouseman);
        createInventoryUseCase.insert(inventory);
    }

    private static void setupDatabase() {
        DatabaseBuilder dbBuilder = new DatabaseBuilder();
        dbBuilder.buildDatabaseIfMissing();
    }

    private static void configureInjection() {
        PersonDAO personDAO = new InMemoryPersonDAO();
        createPersonUseCase = new CreatePersonUseCase(personDAO);
        updatePersonUseCase = new UpdatePersonUseCase(personDAO);
        findPersonUseCase = new FindPersonUseCase(personDAO);
        deletePersonUseCase = new DeletePersonUseCase(personDAO);

        ItemDAO itemDAO = new InMemoryItemDAO();
        createItemUseCase = new CreateItemUseCase(itemDAO);
        updateItemUseCase = new UpdateItemUseCase(itemDAO);
        findItemUseCase = new FindItemUseCase(itemDAO);
        deleteItemUseCase = new DeleteItemUseCase(itemDAO);

        PlaceDAO placeDAO = new InMemoryPlaceDAO();
        createPlaceUseCase = new CreatePlaceUseCase(placeDAO);
        updatePlaceUseCase = new UpdatePlaceUseCase(placeDAO);
        findPlaceUseCase = new FindPlaceUseCase(placeDAO);
        deletePlaceUseCase = new DeletePlaceUseCase(placeDAO);

        GoodsDAO goodsDAO = new InMemoryGoodsDAO();
        createGoodsUseCase = new CreateGoodsUseCase(goodsDAO);
        updateGoodsUseCase = new UpdateGoodsUseCase(goodsDAO);
        findGoodsUseCase = new FindGoodsUseCase(goodsDAO);
        deleteGoodsUseCase = new DeleteGoodsUseCase(goodsDAO);

        CategoryDAO categoryDAO = new InMemoryCategoryDAO();
        createCategoryUseCase = new CreateCategoryUseCase(categoryDAO);
        updateCategoryUseCase = new UpdateCategoryUseCase(categoryDAO);
        findCategoryUseCase = new FindCategoryUseCase(categoryDAO);
        deleteCategoryUseCase = new DeleteCategoryUseCase(categoryDAO);

        InventoryDAO inventoryDAO = new InMemoryInventoryDAO();
        createInventoryUseCase = new CreateInventoryUseCase(inventoryDAO);
        findInventoryUseCase = new FindInventoryUseCase(inventoryDAO);

        RegisterDAO registerDAO = new InMemoryRegisterDAO();
        createRegisterUseCase = new CreateRegisterUseCase(registerDAO);
        findRegisterUseCase = new FindRegisterUseCase(registerDAO);
    }

    private void alerta(String titulo, String cabecalho, String conteudo) {
        Alert alerta = new Alert(Alert.AlertType.ERROR);
        alerta.setTitle(titulo);
        alerta.setHeaderText(cabecalho);
        alerta.setContentText(conteudo);
        alerta.showAndWait();
    }
}
