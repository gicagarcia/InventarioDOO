package br.edu.ifsp.inventariodoo.domain.usecases.inventory;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityAlreadyExistsException;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Notification;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

public class CreateInventoryUseCase {

    private InventoryDAO inventoryDAO;

    public CreateInventoryUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public Integer insert(Inventory inventory){
        Validator<Inventory> validator = new InventoryInputRequestValidator();
        Notification notification = validator.validate(inventory);

        if(notification.hasErrors())
            throw new IllegalArgumentException(notification.errorMessage());

        Integer id = inventory.getId();
        if(inventoryDAO.findOne(id).isPresent())
            throw new EntityAlreadyExistsException("This ID is already in use");
        
        return inventoryDAO.create(inventory);
    }
}
