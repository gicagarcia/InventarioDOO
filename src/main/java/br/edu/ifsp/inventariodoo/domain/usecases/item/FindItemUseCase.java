package br.edu.ifsp.inventariodoo.domain.usecases.item;

import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindItemUseCase {
    private ItemDAO itemDAO;

    public FindItemUseCase(ItemDAO itemDAO) {
        this.itemDAO = itemDAO;
    }

    public Optional<Item> findByTag(String tag){
        if(Validator.nullOrEmpty(tag))
            throw new IllegalArgumentException("Tag can not be null or empty.");
        return itemDAO.findByTag(tag);
    }

    public List<Item> findAll(){
        return itemDAO.findAll();
    }
}
