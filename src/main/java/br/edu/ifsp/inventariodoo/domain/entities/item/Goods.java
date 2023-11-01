package br.edu.ifsp.inventariodoo.domain.entities.item;

public class Goods {
    private Integer id;
    private String name;
    private String origin;
    private String characteristics;
    private Category category;

    public Goods() {
    }

    public Goods(String name, String origin, String characteristics, Category category) {
        this(null, name, origin, characteristics, category);
    }

    public Goods(Integer id, String name, String origin, String characteristics, Category category) {
        this.id = id;
        this.name = name;
        this.origin = origin;
        this.characteristics = characteristics;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(String characteristics) {
        this.characteristics = characteristics;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", origin='" + origin + '\'' +
                ", characteristics='" + characteristics + '\'' +
                ", category=" + category +
                '}';
    }
}
