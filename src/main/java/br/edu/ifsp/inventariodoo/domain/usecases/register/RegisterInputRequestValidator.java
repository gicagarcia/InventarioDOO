package br.edu.ifsp.inventariodoo.domain.usecases.register;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class RegisterInputRequestValidator extends Validator<Register> {
    @Override
    public Notification validate(Register register) {
        Notification notification = new Notification();

        if (register == null){
            notification.addError("Register is null");
            return notification;
        }
        if(nullOrEmpty(register.getDescription()))
            notification.addError("Description is null or empty");
        if (register.getRegisterDate()==null){
            notification.addError("Register Date is null");
        }
        if (register.getItem()==null){
            notification.addError("Item is null");
        }

        if (register.getInventor()==null){
            notification.addError("Inventor is null");
        }
        if (register.getPlace()==null){
            notification.addError("Place is null");
        }
        if (register.getStatus()==null){
            notification.addError("Status is null");
        }

        return notification;
    }
}
