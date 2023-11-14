package br.edu.ifsp.inventariodoo.domain.usecases.alterItem;

import br.edu.ifsp.inventariodoo.domain.entities.item.AlterPersonItem;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class CreateAlterPersonItem {
    private AlterPersonDAO alterPersonDAO;

    public CreateAlterPersonItem(AlterPersonDAO alterPersonDAO) {
        this.alterPersonDAO = alterPersonDAO;
    }

    public Integer insert(AlterPersonItem alterPersonItem){
        Validator<AlterPersonItem> validator = new AlterPersonInputRequestValidator();
        Notification notification = validator.validate(alterPersonItem);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());


        return alterPersonDAO.create(alterPersonItem);
    }
}
