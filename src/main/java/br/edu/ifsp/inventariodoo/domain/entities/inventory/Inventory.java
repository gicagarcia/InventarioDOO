package br.edu.ifsp.inventariodoo.domain.entities.inventory;

import br.edu.ifsp.inventariodoo.domain.entities.user.Person;

import java.util.List;

public class Inventory {
    private Integer id;
    private Person president;
    private List<Person> inventors;
    private List<Register> itensInventoried;

    public Inventory() {
    }

    public Inventory(Person president, List<Person> inventors, List<Register> itensInventoried) {
        this.president = president;
        this.inventors = inventors;
        this.itensInventoried = itensInventoried;
    }

    public Inventory(Integer id, Person president, List<Person> inventors, List<Register> itensInventoried) {
        this.id = id;
        this.president = president;
        this.inventors = inventors;
        this.itensInventoried = itensInventoried;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Inventory{" +
                "id=" + id +
                ", president=" + president +
                ", inventors=" + inventors +
                ", itensInventoried=" + itensInventoried +
                '}';
    }
}
