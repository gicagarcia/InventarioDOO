package br.edu.ifsp.inventariodoo.application.repository;

import br.edu.ifsp.inventariodoo.domain.usecases.category.CategoryDAO;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;

import java.util.*;

public class InMemoryCategoryDAO implements CategoryDAO {

    private static final Map<Integer, Category> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Optional<Category> findByName(String name) {
        return db.values().stream()
                .filter(category -> category.getName().equals(name))
                .findAny();
    }

    @Override
    public Integer create(Category category) {
        idCounter++;
        category.setId(idCounter);
        db.put(idCounter, category);
        return idCounter;
    }

    @Override
    public Optional<Category> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Category> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Category category) {
        Integer id = category.getId();
        if (db.containsKey(id)) {
            db.replace(id, category);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        if (db.containsKey(key)){
            db.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Category category) {
        return deleteByKey(category.getId());
    }
}
