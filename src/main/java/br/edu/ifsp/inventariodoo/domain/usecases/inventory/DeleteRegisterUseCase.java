package br.edu.ifsp.inventariodoo.domain.usecases.inventory;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.GoodsDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;

public class DeleteRegisterUseCase {

    private RegisterDAO registerDAO;

    public DeleteRegisterUseCase(RegisterDAO registerDAO) {
        this.registerDAO = registerDAO;
    }

    public boolean delete(Integer id){
        if(id == null || registerDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Register not found");
        return registerDAO.deleteByKey(id);

    }

    public boolean delete(Register register){
        if(register == null || registerDAO.findOne(register.getId()).isEmpty())
            throw new EntityNotFoundException("Register not found");
        return registerDAO.delete(register);
    }
}
