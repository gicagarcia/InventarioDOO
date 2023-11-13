package br.edu.ifsp.inventariodoo.domain.usecases.inventory;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

import java.util.List;

public class InventoryInputRequestValidator extends Validator<Inventory> {
    @Override
    public Notification validate(Inventory inventory) {
        Notification notification = new Notification();

        if (inventory == null){
            notification.addError("Inventory is null");
            return notification;
        }
        if(inventory.getPresident()==null)
            notification.addError("President of inventory is null");
        List<Person> inventors = inventory.getInventors();
        if (inventors == null || inventors.isEmpty()){
            notification.addError("Inventors of inventory is null");
        }
        List<Register> items_inventoried = inventory.getItensInventoried();
        if (items_inventoried==null || items_inventoried.isEmpty()){
            notification.addError("Itens inventoried of inventory is null");
        }


        return notification;
    }
}
