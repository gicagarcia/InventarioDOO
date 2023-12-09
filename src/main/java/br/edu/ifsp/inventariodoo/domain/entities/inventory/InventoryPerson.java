package br.edu.ifsp.inventariodoo.domain.entities.inventory;

public class InventoryPerson {
    private Integer inventoryId;
    private String personRegistrationId;

    public Integer getInventoryId() {
        return inventoryId;
    }

    public void setInventoryId(Integer inventoryId) {
        this.inventoryId = inventoryId;
    }

    public String getPersonRegistrationId() {
        return personRegistrationId;
    }

    public void setPersonRegistrationId(String personRegistrationId) {
        this.personRegistrationId = personRegistrationId;
    }
}
