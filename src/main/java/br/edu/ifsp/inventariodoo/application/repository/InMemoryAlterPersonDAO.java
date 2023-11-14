package br.edu.ifsp.inventariodoo.application.repository;

import br.edu.ifsp.inventariodoo.domain.entities.item.AlterPersonItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.usecases.alterItem.AlterPersonDAO;

import java.util.*;

public class InMemoryAlterPersonDAO implements AlterPersonDAO {
    private static final Map<Integer, AlterPersonItem> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(AlterPersonItem alterPersonItem) {
        idCounter++;
        alterPersonItem.setId(idCounter);
        db.put(idCounter, alterPersonItem);
        Item item = alterPersonItem.getItem();
        item.setResponsible(alterPersonItem.getNewResponsible()); //Precisa dar update
        return idCounter;
    }

    @Override
    public Optional<AlterPersonItem> findOne(Integer id) {
        return db.values().stream()
                .filter(alterPersonItem -> alterPersonItem.getId().equals(id))
                .findAny();
    }

    @Override
    public List<AlterPersonItem> findAll() {
        return new ArrayList<>(db.values());
    }
}
