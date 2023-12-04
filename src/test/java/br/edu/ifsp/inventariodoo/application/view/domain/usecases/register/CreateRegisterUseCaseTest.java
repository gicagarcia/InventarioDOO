package br.edu.ifsp.inventariodoo.application.view.domain.usecases.register;

import br.edu.ifsp.inventariodoo.application.repository.inmemory.InMemoryRegisterDAO;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.usecases.register.CreateRegisterUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.register.RegisterDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class CreateRegisterUseCaseTest {
    private CreateRegisterUseCase createRegisterUseCase;
    private RegisterDAO registerDAO;

    @BeforeEach
    void config(){
        RegisterDAO registerDAO = new InMemoryRegisterDAO();
        createRegisterUseCase = new CreateRegisterUseCase(registerDAO);
    }

    @Test
    void CreateValidRegister(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);

        LocalDate date = LocalDate.of(2023, 11, 15);
        Register register1 = new Register(date,place1,item1,person1,"teste", StatusItem.ABSENT);
        assertNotNull(createRegisterUseCase.insert(register1));
    }

    @Test
    void CreateRegisterWithoutDate(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);

        Register register1 = new Register(null,place1,item1,person1,"oi", StatusItem.ABSENT);
        assertThrows(IllegalArgumentException.class, () -> createRegisterUseCase.insert(register1));
    }

    @Test
    void CreateRegisterWithoutPlace(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);

        LocalDate date = LocalDate.of(2023, 11, 15);
        Register register1 = new Register(date,null,item1,person1,"oi", StatusItem.ABSENT);

        assertThrows(IllegalArgumentException.class, () -> createRegisterUseCase.insert(register1));
    }

    @Test
    void CreateRegisterWithoutItem(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);

        LocalDate date = LocalDate.of(2023, 11, 15);
        Register register1 = new Register(date,place1,null,person1,"oi", StatusItem.ABSENT);

        assertThrows(IllegalArgumentException.class, () -> createRegisterUseCase.insert(register1));
    }

    @Test
    void CreateRegisterWithoutPerson(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);

        LocalDate date = LocalDate.of(2023, 11, 15);
        Register register1 = new Register(date,place1,item1,null,"oi", StatusItem.ABSENT);

        assertThrows(IllegalArgumentException.class, () -> createRegisterUseCase.insert(register1));
    }

    @Test
    void CreateRegisterWithoutDescription(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);

        LocalDate date = LocalDate.of(2023, 11, 15);
        Register register1 = new Register(date,place1,item1,person1,null, StatusItem.ABSENT);

        assertThrows(IllegalArgumentException.class, () -> createRegisterUseCase.insert(register1));
    }

    @Test
    void CreateRegisterWithoutStatus(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);

        LocalDate date = LocalDate.of(2023, 11, 15);
        Register register1 = new Register(date,place1,item1,person1,"oi", null);

        assertThrows(IllegalArgumentException.class, () -> createRegisterUseCase.insert(register1));
    }

}