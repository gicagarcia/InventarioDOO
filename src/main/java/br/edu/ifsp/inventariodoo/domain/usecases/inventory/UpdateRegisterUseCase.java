package br.edu.ifsp.inventariodoo.domain.usecases.inventory;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.item.Goods;
import br.edu.ifsp.inventariodoo.domain.usecases.goods.GoodsDAO;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class UpdateRegisterUseCase {
    private RegisterDAO registerDAO;

    public UpdateRegisterUseCase(RegisterDAO registerDAO) {
        this.registerDAO = registerDAO;
    }

    public boolean update(Register register){
        Validator<Register> validator = new RegisterInputRequestValidator();
        Notification notification = validator.validate(register);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer id = register.getId();
        if(registerDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Register not found");

        return registerDAO.update(register);
    }
}
