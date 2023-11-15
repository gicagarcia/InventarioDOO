package br.edu.ifsp.inventariodoo.domain.usecases.inventory;

import br.edu.ifsp.inventariodoo.application.repository.InMemoryInventoryDAO;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.entities.user.SecretPhrase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CreateInventoryUseCaseTest {
    private CreateInventoryUseCase createInventoryUseCase;
    private InventoryDAO inventoryDAO;
    private FindInventoryUseCase findInventoryUseCase;

    @BeforeEach
    void config(){
        InventoryDAO inventoryDAO = new InMemoryInventoryDAO();
        createInventoryUseCase = new CreateInventoryUseCase(inventoryDAO);
        findInventoryUseCase = new FindInventoryUseCase(inventoryDAO);
    }

    @Test
    void CreateValidInventory(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods("notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);
        LocalDate date = LocalDate.of(2023, 11, 15);
        Register register1 = new Register(date,place1,item1,person1,"teste", StatusItem.ABSENT);

        Person premier = Person.asPremier("345", "Andreia", "andreia@gmail.com", "999",
                "ancora");
        //Secret phrase
        List<SecretPhrase> phrases2 = new ArrayList<>();
        premier.setSecretPhrases(phrases2);
        premier.registerSecretPhrase("Nome da cachorra", "Monica");

        List<Register> list1 = new ArrayList<>();
        list1.add(register1);
        List<Person> inventors = new ArrayList<>();
        inventors.add(person1);

        //List<Inventory> in = findInventoryUseCase.findAll();
        //System.out.println(in);

        Inventory inventory1 = new Inventory(premier,inventors,list1);
        assertNotNull(createInventoryUseCase.insert(inventory1));
    }

    @Test
    void CreateInventoryWithoutPresident(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);
        LocalDate date = LocalDate.of(2023, 11, 15);
        Register register1 = new Register(date,place1,item1,person1,"teste", StatusItem.ABSENT);

        Person premier = Person.asPremier("345", "Andreia", "andreia@gmail.com", "999",
                "ancora");
        //Secret phrase
        List<SecretPhrase> phrases2 = new ArrayList<>();
        premier.setSecretPhrases(phrases2);
        premier.registerSecretPhrase("Nome da cachorra", "Monica");

        List<Register> list1 = new ArrayList<>();
        list1.add(register1);
        List<Person> inventors = new ArrayList<>();
        inventors.add(person1);

        Inventory inventory1 = new Inventory(null,inventors,list1);
        assertThrows(IllegalArgumentException.class, () -> createInventoryUseCase.insert(inventory1));
    }

    @Test
    void CreateInventoryWithoutInventor(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);
        LocalDate date = LocalDate.of(2023, 11, 15);
        Register register1 = new Register(date,place1,item1,person1,"teste", StatusItem.ABSENT);

        Person premier = Person.asPremier("345", "Andreia", "andreia@gmail.com", "999",
                "ancora");
        //Secret phrase
        List<SecretPhrase> phrases2 = new ArrayList<>();
        premier.setSecretPhrases(phrases2);
        premier.registerSecretPhrase("Nome da cachorra", "Monica");

        List<Register> list1 = new ArrayList<>();
        list1.add(register1);
        List<Person> inventors = new ArrayList<>();
        inventors.add(person1);

        Inventory inventory1 = new Inventory(premier,null,list1);
        assertThrows(IllegalArgumentException.class, () -> createInventoryUseCase.insert(inventory1));
    }

    @Test
    void CreateInventoryWithoutRegisters(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);
        LocalDate date = LocalDate.of(2023, 11, 15);
        Register register1 = new Register(date,place1,item1,person1,"teste", StatusItem.ABSENT);

        Person premier = Person.asPremier("345", "Andreia", "andreia@gmail.com", "999",
                "ancora");
        //Secret phrase
        List<SecretPhrase> phrases2 = new ArrayList<>();
        premier.setSecretPhrases(phrases2);
        premier.registerSecretPhrase("Nome da cachorra", "Monica");

        List<Register> list1 = new ArrayList<>();
        list1.add(register1);
        List<Person> inventors = new ArrayList<>();
        inventors.add(person1);

        Inventory inventory1 = new Inventory(premier,inventors,null);
        assertThrows(IllegalArgumentException.class, () -> createInventoryUseCase.insert(inventory1));
    }

}