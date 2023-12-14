package br.edu.ifsp.inventariodoo.domain.usecases.register;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class CreateRegisterUseCase {

    private RegisterDAO registerDAO;

    public CreateRegisterUseCase(RegisterDAO registerDAO) {
        this.registerDAO = registerDAO;
    }

    public Integer insert(Register register){
        Validator<Register> validator = new RegisterInputRequestValidator();
        Notification notification = validator.validate(register);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

//        Integer id = register.getId();
//        if(registerDAO.findOne(id).isPresent())
//            throw new EntityAlreadyExistsException("This ID is already in use");
        
        return registerDAO.create(register);
    }
}
