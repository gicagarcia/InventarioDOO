package br.edu.ifsp.inventariodoo.domain.entities.inventory;

import br.edu.ifsp.inventariodoo.domain.entities.user.Person;

import java.util.List;

public class Inventory {
    Person president;
    List<Person> inventors;
    List<Register> itensInventoried;

    public Inventory() {
    }

    public Inventory(Person president, List<Person> inventors, List<Register> itensInventoried) {
        this.president = president;
        this.inventors = inventors;
        this.itensInventoried = itensInventoried;
    }

    public Person getPresident() {
        return president;
    }

    public void setPresident(Person president) {
        this.president = president;
    }

    public List<Person> getInventors() {
        return inventors;
    }

    public void setInventors(List<Person> inventors) {
        this.inventors = inventors;
    }

    public List<Register> getItensInventoried() {
        return itensInventoried;
    }

    public void setItensInventoried(List<Register> itensInventoried) {
        this.itensInventoried = itensInventoried;
    }
}
