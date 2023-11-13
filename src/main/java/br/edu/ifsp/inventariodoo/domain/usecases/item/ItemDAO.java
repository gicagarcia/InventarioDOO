package br.edu.ifsp.inventariodoo.domain.usecases.item;

import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.DAO;

import java.util.Optional;

public interface ItemDAO extends DAO<Item, String> {

}
