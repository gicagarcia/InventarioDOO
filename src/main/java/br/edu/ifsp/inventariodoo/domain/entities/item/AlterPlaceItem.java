package br.edu.ifsp.inventariodoo.domain.entities.item;

import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;

import java.time.LocalDate;

public class AlterPlaceItem {
    Integer id;
    private LocalDate dateOfAlter;
    private Item item;
    private Place oldPlace;
    private Place newPlace;

    public AlterPlaceItem() {
    }

    public AlterPlaceItem(Item item, Place newPlace) {
        LocalDate today = LocalDate.now();
        this.dateOfAlter = today;
        this.item = item;
        this.oldPlace = item.getPlace();
        this.newPlace = newPlace;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public LocalDate getDateOfAlter() {
        return dateOfAlter;
    }

    public void setDateOfAlter(LocalDate dateOfAlter) {
        this.dateOfAlter = dateOfAlter;
    }

    public Place getOldPlace() {
        return oldPlace;
    }

    public void setOldPlace(Place oldPlace) {
        this.oldPlace = oldPlace;
    }

    public Place getNewPlace() {
        return newPlace;
    }

    public void setNewPlace(Place newPlace) {
        this.newPlace = newPlace;
    }

    @Override
    public String toString() {
        return "AlterPlaceItem{" +
                "dateOfAlter=" + dateOfAlter +
                ", oldPlace=" + oldPlace +
                ", newPlace=" + newPlace +
                '}';
    }
}
