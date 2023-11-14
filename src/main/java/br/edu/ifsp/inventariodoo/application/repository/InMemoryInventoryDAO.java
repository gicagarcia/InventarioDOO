package br.edu.ifsp.inventariodoo.application.repository;

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
    public List<Inventory> filterByInventor(Person inventor) {
        return db.values().stream()
                .filter(inventory -> inventory.getInventors().equals(inventor))
                .collect(Collectors.toList());
    }
    public List<Inventory> filterByPlace(Place place) {
        return db.values().stream()
                .filter(register -> register.getItensInventoried().equals(place))
                .collect(Collectors.toList());
    }
    public List<Inventory> filterByStatus(StatusItem status) {
        return db.values().stream()
                .filter(inventory -> inventory.getItensInventoried().equals(status))
                .collect(Collectors.toList());
    }
    public List<Inventory> filterByResponsiblePerson(Person responsiblePerson) {
        return db.values().stream()
                .filter(inventory -> inventory.getPresident().equals(responsiblePerson))
                .collect(Collectors.toList());
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

    @Override
    public boolean deleteByKey(Integer id) {
        if(db.containsKey(id)){
            db.remove(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Inventory inventory) {
        return deleteByKey(inventory.getId());}
}
