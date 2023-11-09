package br.edu.ifsp.inventariodoo.application.repository;

import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.usecases.place.PlaceDAO;

import java.util.*;

public class InMemoryPlaceDAO implements PlaceDAO {
    private static final Map<Integer, Place> db = new LinkedHashMap<>();
    private static int idCounter;

    @Override
    public Integer create(Place place) {
        idCounter++;
        place.setId(idCounter);
        db.put(idCounter, place);
        return idCounter;
    }

    @Override
    public Optional<Place> findOne(Integer key) {
        if(db.containsKey(key))
            return Optional.of(db.get(key));
        return Optional.empty();
    }

    @Override
    public List<Place> findAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public boolean update(Place place) {
        Integer id = place.getId();
        if (db.containsKey(id)) {
            db.replace(id, place);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteByKey(Integer key) {
        if(db.containsKey(key)){
            db.remove(key);
            return true;
        }
        return false;
    }

    @Override
    public boolean delete(Place place) {
        return deleteByKey(place.getId());
    }
}
