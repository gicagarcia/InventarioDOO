package br.edu.ifsp.inventariodoo.domain.usecases.item;

import br.edu.ifsp.inventariodoo.application.repository.InMemoryItemDAO;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeleteItemUseCaseTest {
    private DeleteItemUseCase deleteItemUseCase;
    private ItemDAO itemDAO;
    private CreateItemUseCase createItemUseCase;

    @BeforeEach
    void config(){
        ItemDAO itemDAO = new InMemoryItemDAO();
        deleteItemUseCase = new DeleteItemUseCase(itemDAO);
        createItemUseCase = new CreateItemUseCase(itemDAO);
    }
    @Test
    void DeleteTrue(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);

        createItemUseCase.insert(item1);
        assertTrue(deleteItemUseCase.delete(item1));
    }
    @Test
    void DeleteTrueid(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");
        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);

        createItemUseCase.insert(item1);
        assertTrue(deleteItemUseCase.delete(item1.getTag()));
    }

    @Test
    void DeleteFalse(){
        Item item = new Item();
        assertThrows(EntityNotFoundException.class, () -> deleteItemUseCase.delete(item));
    }
    @Test
    void DeleteFalseid(){
        Item item = new Item();
        assertThrows(EntityNotFoundException.class, () -> deleteItemUseCase.delete(item.getTag()));
    }


}