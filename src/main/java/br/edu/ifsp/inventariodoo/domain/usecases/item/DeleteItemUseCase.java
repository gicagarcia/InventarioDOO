package br.edu.ifsp.inventariodoo.domain.usecases.item;

import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.person.PersonDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;

public class DeleteItemUseCase {
    private ItemDAO itemDAO;

    public DeleteItemUseCase(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public boolean delete(String tag){
        if(tag == null || itemDAO.findOne(tag).isEmpty()) //findOne quando PK
            throw new EntityNotFoundException("Item not found");
        return itemDAO.deleteByKey(tag);
    }


    public boolean delete(Item item){
        if(item == null || itemDAO.findOne(item.getTag()).isEmpty())
            throw new EntityNotFoundException("Item not found");
        return itemDAO.delete(item);
    }
}
