package br.edu.ifsp.inventariodoo.application.repository;

import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.GoodsDAO;

import java.util.*;

public class InMemoryGoodsDAO implements GoodsDAO {

    private static final Map<Integer, Goods> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Optional<Goods> findByName(String name) {
        return db.values().stream()
                .filter(goods -> goods.getName().equals(name))
                .findAny();
    }

    @Override
    public Integer create(Goods goods) {
        idCounter++;
        goods.setId(idCounter);
        db.put(idCounter, goods);
        return idCounter;
    }

    @Override
    public Optional<Goods> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Goods> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Goods goods) {
        Integer id = goods.getId();
        if (db.containsKey(id)) {
            db.replace(id, goods);
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
    public boolean delete(Goods goods) {
        return deleteByKey(goods.getId());
    }
}
