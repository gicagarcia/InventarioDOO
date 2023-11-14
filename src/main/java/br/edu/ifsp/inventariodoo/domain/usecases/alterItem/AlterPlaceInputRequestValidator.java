package br.edu.ifsp.inventariodoo.domain.usecases.alterItem;

import br.edu.ifsp.inventariodoo.domain.entities.item.AlterPlaceItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class AlterPlaceInputRequestValidator extends Validator<AlterPlaceItem> {
    @Override
    public Notification validate(AlterPlaceItem alterPlaceItem) {
        Notification notification = new Notification();

        if (alterPlaceItem == null){
            notification.addError("The alteration is null");
            return notification;
        }
        if(alterPlaceItem.getDateOfAlter() == null)
            notification.addError("Date of alter is null");
        if(alterPlaceItem.getOldPlace() == null)
            notification.addError("Old place is null");
        if(alterPlaceItem.getNewPlace() == null)
            notification.addError("New place is null");

        return notification;
    }
}
