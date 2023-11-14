package br.edu.ifsp.inventariodoo.domain.usecases.inventory;

import br.edu.ifsp.inventariodoo.domain.entities.inventory.Inventory;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.Register;
import br.edu.ifsp.inventariodoo.domain.entities.inventory.StatusItem;
import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;
import br.edu.ifsp.inventariodoo.domain.usecases.utils.Validator;

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

    public Optional<Inventory> findByInventor(Person inventor){
        if(inventor == null) //verifica se o campo é null, ja que o Validator.nullOrEmpty(inventor) nao funciona
            throw new IllegalArgumentException("Inventor of inventory can not be null or empty");
        return inventoryDAO.findByInventor(inventor);
    }

    public Optional<Inventory> findByPlace(Place place){
        if(place == null) //verifica se o campo é null, ja que o Validator.nullOrEmpty() nao funciona
            throw new IllegalArgumentException("Place of inventory can not be null or empty");
        return inventoryDAO.findByPlace(place);
    }
    public Optional<Inventory> findByStatus(StatusItem status){
        if(status == null) //verifica se o campo é null, ja que o Validator.nullOrEmpty() nao funciona
            throw new IllegalArgumentException("Status of inventory can not be null or empty");
        return inventoryDAO.findByStatus(status);
    }
    public Optional<Inventory> findByResponsible(Person responsiblePerson){
        if(responsiblePerson == null) //verifica se o campo é null, ja que o Validator.nullOrEmpty() nao funciona
            throw new IllegalArgumentException("Responsible person of inventory can not be null or empty");
        return inventoryDAO.findByResponsible(responsiblePerson);
    }
    public List<Inventory> findAll(){
        return inventoryDAO.findAll();
    }
}
