package br.edu.ifsp.inventariodoo.domain.usecases.inventory;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;

import java.util.List;
import java.util.Optional;

public class FindInventoryUseCase {
    private InventoryDAO inventoryDAO;

    public FindInventoryUseCase(InventoryDAO inventoryDAO) {
        this.inventoryDAO = inventoryDAO;
    }

    public Optional<Inventory> findOne(Integer id){
        if (id == null)
            throw new IllegalArgumentException("ID can not be null");
        return inventoryDAO.findOne(id);
    }


    public List<Inventory> findAll(){
        return inventoryDAO.findAll();
    }
}
