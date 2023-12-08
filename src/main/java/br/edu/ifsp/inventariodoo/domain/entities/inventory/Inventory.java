package br.edu.ifsp.inventariodoo.domain.entities.inventory;

import br.edu.ifsp.inventariodoo.domain.entities.user.Person;

import java.net.Inet4Address;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private Integer id;
    private Person president;
    private List<Person> inventors;
    private List<Register> itensInventoried; //mudar pra map

    public Inventory() {
    }

    public Inventory(Integer id, Person president, List<Person> inventors, List<Register> itensInventoried) {
        this.id = id;
        this.president = president;
        this.inventors = inventors;
        this.itensInventoried = itensInventoried;
    }

    public static Inventory withLists(Person president, List<Person> inventors, List<Register> itensInventoried) {
        Inventory inventory = new Inventory(null, president, inventors, itensInventoried);
        return inventory;
    }

    public static Inventory withoutLists(Person president){
        List<Person> inventors = new ArrayList<>();
        List<Register> itensInventoried = new ArrayList<>();
        Inventory inventory = new Inventory(null, president, inventors, itensInventoried);
        return inventory;
    }

    public void addInventor(Person inventor){
        this.inventors.add(inventor);
    };

    public void removeInventor(Person inventor){
        this.inventors.remove(inventor);
    }

    public void addRegister(Register register){this.itensInventoried.add(register);}

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
