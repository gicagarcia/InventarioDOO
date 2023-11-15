package br.edu.ifsp.inventariodoo.domain.entities.item;

import br.edu.ifsp.inventariodoo.domain.entities.user.Person;

import java.time.LocalDate;

public class AlterPersonItem {
    Integer id;
    private LocalDate dateOfAlter;
    private Item item;
    private Person oldResponsible;
    private Person newResponsible;

    public AlterPersonItem() {
    }

    public AlterPersonItem(Item item, Person newResponsible) {
        LocalDate today = LocalDate.now();
        this.dateOfAlter = today;
        this.item = item;
        this.oldResponsible = item.getResponsible();
        this.newResponsible = newResponsible;
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

    public Person getOldResponsible() {
        return oldResponsible;
    }

    public void setOldResponsible(Person oldResponsible) {
        this.oldResponsible = oldResponsible;
    }

    public Person getNewResponsible() {
        return newResponsible;
    }

    public void setNewResponsible(Person newResponsible) {
        this.newResponsible = newResponsible;
    }

    @Override
    public String toString() {
        return "AlterPersonItem{" +
                "dateOfAlter=" + dateOfAlter +
                ", oldResponsible=" + oldResponsible +
                ", newResponsible=" + newResponsible +
                '}';
    }
}
