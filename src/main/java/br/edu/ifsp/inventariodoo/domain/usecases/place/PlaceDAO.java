package br.edu.ifsp.inventariodoo.domain.usecases.place;

import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.DAO;

public interface PlaceDAO extends DAO<Place, Integer> {
    //Optional<Place> findByBlock(String registration);
}
