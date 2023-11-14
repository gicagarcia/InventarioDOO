package br.edu.ifsp.inventariodoo.application.repository;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.usecases.inventory.InventoryDAO;

import java.util.*;

public class InMemoryInventoryDAO implements InventoryDAO {
    private static final Map<Integer, Inventory> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(Inventory inventory) {
        idCounter++;
        inventory.setId(idCounter);
        db.put(idCounter, inventory);
        return idCounter;
    }

    @Override
    public Optional<Inventory> findOne(Integer id) {
        return db.values().stream()
                .filter(inventory -> inventory.getId().equals(id))
                .findAny();
    }

    @Override
    public List<Inventory> findAll() {
        return new ArrayList<>(db.values());
    }
}
