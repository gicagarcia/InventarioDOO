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
import br.edu.ifsp.inventariodoo.domain.usecases.inventory.UpdateInventoryUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.item.*;
import br.edu.ifsp.inventariodoo.domain.usecases.person.*;
import br.edu.ifsp.inventariodoo.domain.usecases.place.*;
import br.edu.ifsp.inventariodoo.domain.usecases.register.CreateRegisterUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.register.FindRegisterUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.register.RegisterDAO;
import java.time.LocalDate;
import java.util.*;


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
    public static UpdateInventoryUseCase updateInventoryUseCase;

    public static CreateRegisterUseCase createRegisterUseCase;
    public static FindRegisterUseCase findRegisterUseCase;


    public static void main(String[] args) throws Exception {
        configureInjection();

        setupDatabase();
        //populateInMemory();
        MainUIView.main(args);
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

        // =========== INVENTORY TESTES ============
        Person inventor1 = Person.asPerson("1", "gui", "gui@gmail.com", "123");
        Person inventor2 = Person.asPerson("2", "julia", "julia@gmail.com", "123");
        Person inventor3 = Person.asPerson("3", "joao", "joao@gmail.com", "123");
        createPersonUseCase.insert(inventor3);
        //lista de person
        List<Person> personList1 = new ArrayList<>();
        personList1.add(inventor1);
        personList1.add(inventor2);
        List<Person> personList2 = new ArrayList<>();
        personList2.add(inventor2);
        personList2.add(inventor3);

        Place placeTest = new Place(1, 50, "bloco A");
        createPlaceUseCase.insert(placeTest);
        Place placeTest1 = new Place(2, 50, "bloco A");
        createPlaceUseCase.insert(placeTest1);
        Person responsible = Person.asPremier("22", "giovana", "giovana@gmail.com 1", "123", "123");
        createPersonUseCase.insert(responsible);
        Person responsible1 = Person.asPremier("44", "giovana", "giovana@gmail.com 1", "123", "123");
        createPersonUseCase.insert(responsible1);
        Category category1 = new Category(1, "Roupa", "area 1", "eletrica");
        createCategoryUseCase.insert(category1);
        Goods goods1 = new Goods(1, "Camiseta", "escola", "branca", category1);
        createGoodsUseCase.insert(goods1);
        Item itemTest = new Item("tag2", "sla cara", StatusItem.NEW, goods1, responsible, placeTest);
        createItemUseCase.insert(itemTest);
        LocalDate currentDate = LocalDate.now();

        Register register1 = new Register(1, currentDate, placeTest, itemTest, responsible, "sla", StatusItem.UNUSABLE);
        Register register2 = new Register(2, currentDate, placeTest1, itemTest, responsible, "sla", StatusItem.UNUSABLE);
        createRegisterUseCase.insert(register1);
        createRegisterUseCase.insert(register2);
        List<Register> registerList1 = new ArrayList<>();

        registerList1.add(register1);
        registerList1.add(register2);


        List<Register> registerList2 = new ArrayList<>();
        registerList2.add(register2);


        Inventory inventory1 = new Inventory(1, responsible, personList1, registerList1);
        Inventory inventory2 = new Inventory(2, responsible1, personList2, registerList2);
        createInventoryUseCase.insert(inventory1);
        createInventoryUseCase.insert(inventory2);
        List<Inventory> inventories = findInventoryUseCase.findAll();
        System.out.println("lista de inventory" + inventories);
        Optional<Inventory> inventoryFindOne = findInventoryUseCase.findOne(1);
        System.out.println("inventory do find one: " + inventoryFindOne.toString());

        List<Inventory> inventoryList = new ArrayList<>();
        // Adicione inventários à lista, se necessário

        Optional<List<Inventory>> findByInventor = findInventoryUseCase.findByInventor(inventor3);
        System.out.println("print de findByInventor: " + findByInventor.toString());

        Optional<List<Inventory>> findByPlace = findInventoryUseCase.findByPlace(placeTest1);
        System.out.println("print de findByPlace: " + findByPlace.toString());

        Optional<List<Inventory>> findByStatus = findInventoryUseCase.findByStatus(StatusItem.UNUSABLE);
        System.out.println("find by status: " + findByStatus.toString());

        Optional<List<Inventory>> findByReponsible = findInventoryUseCase.findByResponsible(responsible1);
        System.out.println("find by responsible: " + findByReponsible.toString());

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

        RegisterDAO registerDAO = new SqliteRegisterDAO();
        createRegisterUseCase = new CreateRegisterUseCase(registerDAO);
        findRegisterUseCase = new FindRegisterUseCase(registerDAO);

        InventoryDAO inventoryDAO = new SqliteInventoryDAO(placeDAO, itemDAO, personDAO);
        createInventoryUseCase = new CreateInventoryUseCase(inventoryDAO);
        findInventoryUseCase = new FindInventoryUseCase(inventoryDAO);
        updateInventoryUseCase = new UpdateInventoryUseCase(inventoryDAO);

    }
}
