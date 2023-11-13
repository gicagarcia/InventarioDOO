package br.edu.ifsp.inventariodoo.domain.usecases.inventory;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.EntityNotFoundException;

public class DeleteInventoryUseCase {

    private InventoryDAO inventoryDAO;

    public DeleteInventoryUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public boolean delete(Integer id){
        if(id == null || inventoryDAO.findOne(id).isEmpty())
            throw new EntityNotFoundException("Inventory not found");
        return inventoryDAO.deleteByKey(id);

    }

    public boolean delete(Inventory inventory){
        if(inventory == null || inventoryDAO.findOne(inventory.getId()).isEmpty())
            throw new EntityNotFoundException("Inventory not found");
        return inventoryDAO.delete(inventory);
    }
}
