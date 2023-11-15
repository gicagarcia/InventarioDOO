package br.edu.ifsp.inventariodoo.domain.usecases.alterItem;

import br.edu.ifsp.inventariodoo.domain.entities.item.AlterPlaceItem;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class CreateAlterPlaceItem {
    private AlterPlaceDAO alterPlaceDAO;

    public CreateAlterPlaceItem(AlterPlaceDAO alterPlaceDAO) {
        this.alterPlaceDAO = alterPlaceDAO;
    }

    public Integer insert(AlterPlaceItem alterPlaceItem) {
        Validator<AlterPlaceItem> validator = new AlterPlaceInputRequestValidator();
        Notification notification = validator.validate(alterPlaceItem);

        if (notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());


        return alterPlaceDAO.create(alterPlaceItem);
    }
}
