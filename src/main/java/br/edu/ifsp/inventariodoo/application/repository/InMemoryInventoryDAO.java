package br.edu.ifsp.inventariodoo.application.repository;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
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
    public List<Inventory> findByInventor(Person inventor) {
        return itensInventoried.stream()
                .filter(inventory -> inventory.getInventors().equals(inventor))
                .collect(Collectors.toList());
    }//peguei oq vc mandou no zap, coloquei aqui troquei de Register pra Inventory,
    //troquei register pra inventory dentro de filter tbm mas alguma coisa ta errada

    //ai embaixo em vez de colocar return itensInventoried.stream() que é o que ta no zap, tentei colocar o db.values().stream() igual dos outros que a gente fez

    public List<Register> findByPlace(Integer id, Place place) {
        Inventory inventory = db.get(id);
        if (inventory == null) {
            return Collections.emptyList();
        }

        return inventory.getItensInventoried().stream()
                .filter(register -> register.getPlace().equals(place))
                .collect(Collectors.toList());
    }
    public List<Register> findByStatus(Integer id, StatusItem status) {
        Inventory inventory = db.get(id);
        if (inventory == null) {
            return Collections.emptyList();
        }

        return inventory.getItensInventoried().stream()
                .filter(register -> register.getStatus().equals(status))
                .collect(Collectors.toList());
    }

    public List<Register> findByResponsible(Integer id, Person responsiblePerson) {
        Inventory inventory = db.get(id);
        if (inventory == null) {
            return Collections.emptyList();
        }

        return inventory.getItensInventoried().stream()
                .filter(register -> register.getInventor().equals(responsiblePerson))
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
