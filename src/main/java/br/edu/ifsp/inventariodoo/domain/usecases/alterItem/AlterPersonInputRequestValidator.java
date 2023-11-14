package br.edu.ifsp.inventariodoo.domain.usecases.alterItem;

import br.edu.ifsp.inventariodoo.domain.entities.item.AlterPersonItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Category;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class AlterPersonInputRequestValidator extends Validator<AlterPersonItem> {
    @Override
    public Notification validate(AlterPersonItem alterPersonItem) {
        Notification notification = new Notification();

        if (alterPersonItem == null){
            notification.addError("The alteration is null");
            return notification;
        }
        if(alterPersonItem.getDateOfAlter() == null)
            notification.addError("Date of alter is null");
        if(alterPersonItem.getOldResponsible() == null)
            notification.addError("Old responsible is null");
        if(alterPersonItem.getNewResponsible() == null)
            notification.addError("New responsible is null");

        return notification;
    }
}
