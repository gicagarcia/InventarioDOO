package br.edu.ifsp.inventariodoo.domain.usecases.inventory;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.DAO;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.DAOLimited;

import java.util.Optional;

public interface InventoryDAO extends DAOLimited<Inventory, Integer> {


}
