package br.edu.ifsp.inventariodoo.domain.usecases.register;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.DAO;

import java.util.Optional;

public interface RegisterDAO extends DAO<Register, Integer> {
    Optional<Register> findByItem(Item item);

}
