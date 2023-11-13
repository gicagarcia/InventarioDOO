package br.edu.ifsp.inventariodoo.domain.usecases.inventory;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.GoodsDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

import java.util.List;
import java.util.Optional;

public class FindRegisterUseCase {
    private RegisterDAO registerDAO;

    public FindRegisterUseCase(RegisterDAO registerDAO) {
        this.registerDAO = registerDAO;
    }

    public Optional<Register> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null");
        return registerDAO.findOne(id);
    }

    public Optional<Register> findOneByItem(Item item){
        if(item == null)
            throw new IllegalArgumentException("Item can not be null or empty");
        return registerDAO.findByItem(item);
    }
    public List<Register> findAll(){
        return registerDAO.findAll();
    }
}
