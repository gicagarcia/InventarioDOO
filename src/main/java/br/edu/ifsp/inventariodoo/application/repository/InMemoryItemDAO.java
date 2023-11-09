package br.edu.ifsp.inventariodoo.application.repository;

import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.usecases.item.ItemDAO;

import java.util.*;

public class InMemoryItemDAO implements ItemDAO {

    private static final Map<String, Item> db = new LinkedHashMap<>();

    @Override
    public String create(Item item) {
        db.put(item.getTag(), item);
        return item.getTag();
    }

    @Override
    public Optional<Item> findOne(String key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Item> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Item item) {
        String tag = item.getTag();
        if (db.containsKey(tag)) {
            db.replace(tag, item);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(String key) {
        if (db.containsKey(key)){
            db.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Item item) {
        return deleteByKey(item.getTag());
    }
}
