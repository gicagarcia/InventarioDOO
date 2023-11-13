package br.edu.ifsp.inventariodoo.domain.usecases.inventory;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class UpdateInventoryUseCase {
    private InventoryDAO inventoryDAO;

    public UpdateInventoryUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public boolean update(Inventory inventory){
        Validator<Inventory> validator = new InventoryInputRequestValidator();
        Notification notification = validator.validate(inventory);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer id = inventory.getId();
        if(inventoryDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Inventory not found");

        return inventoryDAO.update(inventory);
    }
}
