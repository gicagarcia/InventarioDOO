package br.edu.ifsp.inventariodoo.domain.usecases.alterItem;


import br.edu.ifsp.inventariodoo.domain.entities.item.AlterPersonItem;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindAlterPersonItem {
    private AlterPersonDAO alterPersonDAO;

    public FindAlterPersonItem(AlterPersonDAO alterPersonDAO) {
        this.alterPersonDAO = alterPersonDAO;
    }

    public Optional<AlterPersonItem> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null");
        return alterPersonDAO.findOne(id);
    }
    public List<AlterPersonItem> findAll(){
        return alterPersonDAO.findAll();
    }
}
