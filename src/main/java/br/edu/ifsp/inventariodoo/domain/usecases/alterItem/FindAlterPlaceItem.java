package br.edu.ifsp.inventariodoo.domain.usecases.alterItem;

import br.edu.ifsp.inventariodoo.domain.entities.item.AlterPlaceItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.usecases.category.CategoryDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindAlterPlaceItem {
    private AlterPlaceDAO alterPlaceDAO;

    public FindAlterPlaceItem(AlterPlaceDAO alterPlaceDAO) {
        this.alterPlaceDAO = alterPlaceDAO;
    }

    public Optional<AlterPlaceItem> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null");
        return alterPlaceDAO.findOne(id);
    }

    public List<AlterPlaceItem> findAll(){
        return alterPlaceDAO.findAll();
    }
}
