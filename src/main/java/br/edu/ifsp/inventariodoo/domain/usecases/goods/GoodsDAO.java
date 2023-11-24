package br.edu.ifsp.inventariodoo.domain.usecases.goods;

import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.DAO;

import java.util.Optional;

public interface GoodsDAO extends DAO<Goods, Integer> {
    Optional<Goods> findByName(String name); //metodo unico de goods procurar por nome
}
