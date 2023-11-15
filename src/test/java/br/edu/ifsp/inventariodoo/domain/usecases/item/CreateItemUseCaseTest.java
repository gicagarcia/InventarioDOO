package br.edu.ifsp.inventariodoo.domain.usecases.item;

import br.edu.ifsp.inventariodoo.application.repository.InMemoryItemDAO;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateItemUseCaseTest {
    private CreateItemUseCase createItemUseCase;
    private ItemDAO itemDAO;

    @BeforeEach
    void config(){
        ItemDAO itemDAO = new InMemoryItemDAO();
        createItemUseCase = new CreateItemUseCase(itemDAO);
    }

    @Test
    void CreateValidItem(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");

        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);
        assertNotNull(createItemUseCase.insert(item1));
    }

    @Test
    void CreateItemWithoutTag(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");

        Item item1 = new Item("", "Note Dell", StatusItem.NEW, goods1, person1, place1);

        assertThrows(IllegalArgumentException.class, () -> createItemUseCase.insert(item1));
    }

    @Test
    void CreateItemWithoutDescription(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");

        Item item1 = new Item("1", "", StatusItem.NEW, goods1, person1, place1);

        assertThrows(IllegalArgumentException.class, () -> createItemUseCase.insert(item1));
    }

    @Test
    void CreateItemWithoutStatus(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");

        Item item1 = new Item("1", "Note Dell", null, goods1, person1, place1);

        assertThrows(IllegalArgumentException.class, () -> createItemUseCase.insert(item1));
    }

    @Test
    void CreateItemWithoutGoods(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");

        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, null, person1, place1);

        assertThrows(IllegalArgumentException.class, () -> createItemUseCase.insert(item1));
    }

    @Test
    void CreateItemWithoutResponsible(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");

        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, null, place1);

        assertThrows(IllegalArgumentException.class, () -> createItemUseCase.insert(item1));
    }

    @Test
    void CreateItemWithoutPlace(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");

        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, null);

        assertThrows(IllegalArgumentException.class, () -> createItemUseCase.insert(item1));
    }

}