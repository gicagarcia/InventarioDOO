package br.edu.ifsp.inventariodoo.domain.usecases.inventory;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.DAOLimited;

import java.util.List;
import java.util.Optional;

public interface InventoryDAO extends DAOLimited<Inventory, Integer> {
    Optional<List<Inventory>> findByInventor(Person inventor);
    Optional<List<Inventory>> findByPlace(Place place);
    Optional<List<Inventory>> findByStatus(StatusItem status);
    Optional<List<Inventory>> findByResponsible(Person responsiblePerson);

}
