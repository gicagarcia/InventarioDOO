package br.edu.ifsp.inventariodoo.application.view.domain.usecases.register;

import br.edu.ifsp.inventariodoo.application.repository.inmemory.InMemoryRegisterDAO;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.register.FindRegisterUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.register.RegisterDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class FindRegisterUseCaseTest {
    private FindRegisterUseCase findRegisterUseCase;
    private RegisterDAO registerDAO;

    @BeforeEach
    void config(){
        RegisterDAO registerDAO = new InMemoryRegisterDAO();
        findRegisterUseCase = new FindRegisterUseCase(registerDAO);

    }
    @Test
    void FindValidRegister(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);

        LocalDate date = LocalDate.of(2023, 11, 15);
        Register register1 = new Register(1,date,place1,item1,person1,"teste", StatusItem.ABSENT);
        assertNotNull(findRegisterUseCase.findOne(register1.getId()));
    }

    @Test
    void FindInvalidRegister(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);

        LocalDate date = LocalDate.of(2023, 11, 15);
        Register register1 = new Register(null,date,place1,item1,person1,"teste", StatusItem.ABSENT);
        assertThrows(IllegalArgumentException.class, () -> findRegisterUseCase.findOne(register1.getId()));
    }


}