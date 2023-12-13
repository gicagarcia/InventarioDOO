package br.edu.ifsp.inventariodoo.application.main;

import br.edu.ifsp.inventariodoo.application.controller.PersonManagementUIView;
import br.edu.ifsp.inventariodoo.application.controller.WarehousemanUIView;
import br.edu.ifsp.inventariodoo.application.repository.inmemory.*;
import br.edu.ifsp.inventariodoo.application.repository.sqlite.*;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.entities.user.TypeWorker;
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
import javafx.application.Application;
import javafx.stage.Window;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public static CreateRegisterUseCase createRegisterUseCase;
    public static FindRegisterUseCase findRegisterUseCase;


    public static void main(String[] args) throws Exception {
        configureInjection();

        setupDatabase();
        populateDataBase();
//
//        WarehousemanUIView.main(args);
    }

    private static void populateDataBase() {
        //===================== PLACE TESTE =====================
        //Place placeTest = new Place(5,50,"bloco A");
        //System.out.println("insert no banco de placeTest");
        //createPlaceUseCase.insert(placeTest);
//        List<Place> places = findPlaceUseCase.findAll();
//        Optional<Place> place = findPlaceUseCase.findOne(1);
//        System.out.println(places);
//        System.out.println(place.toString());
//        place.ifPresent(foundPlace -> {
//            Place place1 = foundPlace;
//            place1.setBlock("bloco atualizado");
//            updatePlaceUseCase.update(place1);
//            System.out.println(place1.toString());
//            System.out.println("deletando place");
//            deletePlaceUseCase.delete(1);
//            Optional<Place> placeDeleted = findPlaceUseCase.findOne(1);
//            System.out.println(placeDeleted);
//        });
        // ===================== CATEGORY TESTE ================
//        Category categoryTest = new Category(1,"Eletronicos","area 1","eletrica");
//        System.out.println("insert no banco de categoryTest");
//        createCategoryUseCase.insert(categoryTest);
//        List<Category> categories = findCategoryUseCase.findAll();
//        Optional<Category> category = findCategoryUseCase.findOne(2);
//        Optional<Category> categoryByname = findCategoryUseCase.findOneByName("Eletronicos");
//        System.out.println(categories);
//        System.out.println(category.toString());
//        System.out.println("Category by name: " + category.toString());
//
//        category.ifPresent(foundCategory -> {
//            Category category1 = foundCategory;
//            category1.setName("nome atualizado");
//            updateCategoryUseCase.update(category1);
//            System.out.println(category1.toString());
//            System.out.println("deletando category");
//            deleteCategoryUseCase.delete(2);
//            Optional<Category> categoryDeleted = findCategoryUseCase.findOne(2);
//            System.out.println(categoryDeleted);
//        });

        // ================ GOODS TESTE ================
//        Category category1 = new Category(1,"Roupa1","area 1","eletrica");
//        createCategoryUseCase.insert(category1);
//        findCategoryUseCase.findOne(1);
//        Goods goodsTest = new Goods(1,"Camiseta","escola","branca",category1);
//        System.out.println("Tentando insert de goodsTest");
//        createGoodsUseCase.insert(goodsTest);
//        List<Goods> goodsList = findGoodsUseCase.findAll();
//        System.out.println("lista de goods" + goodsList);
//        System.out.println("antes de find one");
//        Optional<Goods> goods = findGoodsUseCase.findOne(1);
//        Optional<Goods> goodsByname = findGoodsUseCase.findOneByName("Camiseta");
//        System.out.println("lista de goods" + goodsList);
//        System.out.println("goods do find one: " + goods.toString());
//        System.out.println("Category by name: " + goodsByname.toString());
//
//        goods.ifPresent(foundGoods -> {
//            Goods goods1 = foundGoods;
//            goods1.setName("nome atualizado");
//            updateGoodsUseCase.update(goods1);
//            System.out.println(goods1.toString());
//            System.out.println("deletando goods");
//            deleteGoodsUseCase.delete(1);
//            System.out.println("Procurando goods deletado");
//            Optional<Goods> goodsDeleted = findGoodsUseCase.findOne(1);
//            System.out.println(goodsDeleted);
//        });
            // ========== PERSON TESTES ============
//        Person person1 = Person.asPerson("1", "gui","gui@gmail.com","123");
//        Person person2 = Person.asPremier("2","giovana","giovana@gmail.com 1","123","123");
//        Person person3 = Person.asWarehouseman("3","bruno","bruno@gmail.com 1","123","123");
//        person3.registerSecretPhrase("sua idade","20");
//        createPersonUseCase.insert(person1);
//        createPersonUseCase.insert(person2);
//        createPersonUseCase.insert(person3);
//        System.out.println("finds");
//        Optional<Person> person1Find =findPersonUseCase.findOne("1");
//        Optional<Person> person2Find =findPersonUseCase.findOne("2");
//        Optional<Person> person3Find =findPersonUseCase.findOne("3");
//        List<Person> personList = findPersonUseCase.findAll();
//        System.out.println("lista de person" + personList);
//        Optional<Person> personName = findPersonUseCase.findOneByEmail("gui@gmail.com");
//        System.out.println("person person do find one: " + person1Find.toString());
//        System.out.println("person premier do find one: " + person2Find.toString());
//        System.out.println("person warehouseman do find one: " + person3Find.toString());
//        System.out.println("Person by email: " + personName.toString());
//
//        person1Find.ifPresent(foundPerson1 -> {
//            Person person21 = foundPerson1;
//            person21.setName("nome atualizado");
//            updatePersonUseCase.update(person21);
//            System.out.println(person21.toString());
//            System.out.println("deletando goods");
//            deletePersonUseCase.delete("1");
//            System.out.println("Procurando person deletado");
//            Optional<Person> personDeleted = findPersonUseCase.findOne("1");
//            System.out.println(personDeleted);
//        });

        // ============ ITEM TESTES ==========
//        Place placeTest = new Place(1,50,"bloco A");
//        createPlaceUseCase.insert(placeTest);
//        Person responsible = Person.asPremier("22","giovana","giovana@gmail.com 1","123","123");
//        createPersonUseCase.insert(responsible);
//        Category category1 = new Category(1,"Roupa","area 1","eletrica");
//        createCategoryUseCase.insert(category1);
//        Goods goods1 = new Goods(1,"Camiseta","escola","branca",category1);
//        createGoodsUseCase.insert(goods1);
//        Item itemTest = new Item("tag1","sla cara", StatusItem.NEW,goods1,responsible,placeTest);
//        System.out.println("insert de item:");
//        createItemUseCase.insert(itemTest);
//        List<Item> itemList = findItemUseCase.findAll();
//        System.out.println("lista de item" + itemList);
//        Optional<Item> item = findItemUseCase.findOne("tag1");
//        System.out.println("item do find one: " + item.toString());
//
//
//        item.ifPresent(foundItem -> {
//            Item item1 = foundItem;
//            item1.setDescription("descrição atualizado");
//            updateItemUseCase.update(item1);
//            System.out.println("item atualizado: " + item1.toString());
//            System.out.println("deletando goods");
//            deleteItemUseCase.delete("tag1");
//            System.out.println("Procurando item deletado");
//            Optional<Item> itemDeleted = findItemUseCase.findOne("tag1");
//            System.out.println(itemDeleted);
//        });
        // ===== REGISTER TESTES =======
//        Place placeTest = new Place(1,50,"bloco A");
//        createPlaceUseCase.insert(placeTest);
//        Person responsible = Person.asPremier("22","giovana","giovana@gmail.com 1","123","123");
//        createPersonUseCase.insert(responsible);
//        Category category1 = new Category(1,"Roupa","area 1","eletrica");
//        createCategoryUseCase.insert(category1);
//        Goods goods1 = new Goods(1,"Camiseta","escola","branca",category1);
//        createGoodsUseCase.insert(goods1);
//        Item itemTest = new Item("tag1","sla cara", StatusItem.NEW,goods1,responsible,placeTest);
//        createItemUseCase.insert(itemTest);
//        LocalDate currentDate = LocalDate.now();
//        Register registerTeste = new Register(1,currentDate,placeTest, itemTest, responsible, "sla",StatusItem.UNUSABLE);
//        createRegisterUseCase.insert(registerTeste);
//        List<Register> registerList = findRegisterUseCase.findAll();
//        System.out.println("lista de register" + registerList);
//        Optional<Register> register = findRegisterUseCase.findOne(1);
//        System.out.println("register do find one: " + register.toString());



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

        RegisterDAO registerDAO = new SqliteRegisterDAO();
        createRegisterUseCase = new CreateRegisterUseCase(registerDAO);
        findRegisterUseCase = new FindRegisterUseCase(registerDAO);

        InventoryDAO inventoryDAO = new InMemoryInventoryDAO();
        createInventoryUseCase = new CreateInventoryUseCase(inventoryDAO);
    }
}
