package br.edu.ifsp.inventariodoo.domain.entities.item;


import br.edu.ifsp.inventariodoo.domain.entities.user.Person;

public class Item {
    private String tag;
    private String description;
    private String status;
    private Goods goods;
    private Person responsible;
    private Place place;

    public Item() {
        //Setar Almoxarife como responsável e Almoxarifado como local
    }

    public Item(String tag, String description, String status, Goods goods) {
        this.tag = tag;
        this.description = description;
        this.status = status;
        this.goods = goods;
        //Setar Almoxarife como responsável e Almoxarifado como local, usar getSession.loggedUser
    }

    public Item(String tag, String description, String status, Goods goods, Person responsible, Place place) {
        this.tag = tag;
        this.description = description;
        this.status = status;
        this.goods = goods;
        this.responsible = responsible;
        this.place = place;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public Person getResponsible() {
        return responsible;
    }

    public void setResponsible(Person responsible) {
        this.responsible = responsible;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }

    @Override
    public String toString() {
        return "Item{" +
                "tag='" + tag + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", goods=" + goods +
                ", responsible=" + responsible +
                ", place=" + place +
                '}';
    }
}
