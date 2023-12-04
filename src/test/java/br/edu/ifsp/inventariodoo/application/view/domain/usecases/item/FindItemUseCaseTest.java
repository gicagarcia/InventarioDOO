package br.edu.ifsp.inventariodoo.application.view.domain.usecases.item;

import br.edu.ifsp.inventariodoo.application.repository.inmemory.InMemoryItemDAO;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.item.FindItemUseCase;
import br.edu.ifsp.inventariodoo.domain.usecases.item.ItemDAO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FindItemUseCaseTest {
    private FindItemUseCase findItemUseCase;
    private ItemDAO itemDAO;

    @BeforeEach
    void config(){
        ItemDAO itemDAO = new InMemoryItemDAO();
        findItemUseCase = new FindItemUseCase(itemDAO);

    }
    @Test
    void FindValidItem(){
        Category category1 = new Category("hardware", "centro", "aplicacao");
        Goods goods1 = new Goods(1,"notebook","escola","i3 processador" ,category1);
        Place place1 = new Place(65,"bloco 1");
        Person person1 = Person.asPerson("123","Maria joaquina","sla@gmail","123");

        Item item1 = new Item("1", "Note Dell", StatusItem.NEW, goods1, person1, place1);

        assertNotNull(findItemUseCase.findOne(item1.getTag()));
    }

    @Test
    void FindInvalidItem(){
        Item item1 = new Item();
        assertThrows(IllegalArgumentException.class, () -> findItemUseCase.findOne(item1.getTag()));
    }


}