package br.edu.ifsp.inventariodoo.application.repository.inmemory;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.inventory.InventoryDAO;

import java.util.*;
import java.util.stream.Collectors;

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
    public Optional<List<Inventory>> findByInventor(Person inventor) {
        List<Inventory> inventories = db.values().stream()
                .filter(inventory -> inventory.getInventors().contains(inventor))
                .collect(Collectors.toList());

        return Optional.of(inventories);
    }


    public Optional<List<Inventory>> findByPlace(Place place) {
        List<Inventory> inventoriesPlaces = db.values().stream()
                .filter(inventory -> inventory.getItensInventoried().stream()
                        .allMatch(register -> register.getPlace().equals(place)))
                .collect(Collectors.toList());

        return Optional.of(inventoriesPlaces);
    }
    public Optional<List<Inventory>> findByStatus(StatusItem status) {
        List<Inventory> inventoriesPlaces = db.values().stream()
                .filter(inventory -> inventory.getItensInventoried().stream()
                        .allMatch(register -> register.getStatus().equals(status)))
                .collect(Collectors.toList());

        return Optional.of(inventoriesPlaces);
    }
    public Optional<List<Inventory>> findByResponsible(Person responsiblePerson) {
        List<Inventory> responsible = db.values().stream()
                .filter(inventory -> inventory.getPresident().equals(responsiblePerson))
                .collect(Collectors.toList());

        return Optional.of(responsible);

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

    @Override
    public boolean update(Inventory inventory) {
        Integer id = inventory.getId();
        if (db.containsKey(id)) {
            db.replace(id, inventory);
            return true;
        }
        return false;
    }
}
