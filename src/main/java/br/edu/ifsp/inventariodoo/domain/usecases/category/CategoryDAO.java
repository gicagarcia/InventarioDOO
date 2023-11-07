package br.edu.ifsp.inventariodoo.domain.usecases.category;

import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.DAO;

import java.util.Optional;

public interface CategoryDAO extends DAO<Category, Integer> {
    Optional<Category> findByName(String name); //metodo unico de Category
}
