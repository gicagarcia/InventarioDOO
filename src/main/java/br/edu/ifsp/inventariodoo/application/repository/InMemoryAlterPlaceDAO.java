package br.edu.ifsp.inventariodoo.application.repository;


import br.edu.ifsp.inventariodoo.domain.entities.item.AlterPlaceItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.usecases.alterItem.AlterPlaceDAO;

import java.util.*;

public class InMemoryAlterPlaceDAO implements AlterPlaceDAO {
    private static final Map<Integer, AlterPlaceItem> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(AlterPlaceItem alterPlaceItem) {
        idCounter++;
        alterPlaceItem.setId(idCounter);
        db.put(idCounter, alterPlaceItem);
        Item item = alterPlaceItem.getItem();
        item.setPlace(alterPlaceItem.getNewPlace()); //Precisa dar update
        return idCounter;
    }

    @Override
    public Optional<AlterPlaceItem> findOne(Integer id) {
        return db.values().stream()
                .filter(alterPlaceItem -> alterPlaceItem.getId().equals(id))
                .findAny();
    }

    @Override
    public List<AlterPlaceItem> findAll() {
        return new ArrayList<>(db.values());
    }
}

