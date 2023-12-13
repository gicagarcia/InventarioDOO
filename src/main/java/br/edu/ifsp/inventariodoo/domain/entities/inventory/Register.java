package br.edu.ifsp.inventariodoo.domain.entities.inventory;

import br.edu.ifsp.inventariodoo.domain.entities.item.Item;
import br.edu.ifsp.inventariodoo.domain.entities.item.Place;
import br.edu.ifsp.inventariodoo.domain.entities.user.Person;

import java.time.LocalDate;

public class Register {
    private Integer id;
    private LocalDate registerDate;
    private Place place;
    private Item item;
    private Person inventor;
    private String description;
    private StatusItem status;


    public Register(LocalDate registerDate, Place place, Item item, Person inventor, String description, StatusItem status) {
        this(null,registerDate, place, item, inventor,  description,  status);
    }

    public Register(Place place, Item item, Person inventor, String description, StatusItem status){
        this(null, LocalDate.now(), place, item, inventor, description, status);
    }

    public Register(Integer id, LocalDate registerDate, Place place, Item item, Person inventor, String description, StatusItem status) {
        this.id = id;
        this.registerDate = registerDate;
        this.place = place;
        this.item = item;
        this.inventor = inventor;
        this.description = description;
        this.status = status;
    }

    public Register() {//construtor vazio criei para usar no SqliteInventoryDAO

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(LocalDate registerDate) {
        this.registerDate = registerDate;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Person getInventor() {
        return inventor;
    }

    public void setInventor(Person inventor) {
        this.inventor = inventor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusItem getStatus() {
        return status;
    }

    public void setStatus(StatusItem status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Register{" +
                "id=" + id +
                ", registerDate=" + registerDate +
                ", place=" + place +
                ", item=" + item +
                ", inventor=" + inventor +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
