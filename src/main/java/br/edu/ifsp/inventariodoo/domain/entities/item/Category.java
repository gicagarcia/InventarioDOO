package br.edu.ifsp.inventariodoo.domain.entities.item;

public class Category {
    private Integer id;
    private String name;
    private String area;
    private String application;

    public Category() {
    }

    public Category(String name, String area, String application) {

        this.name = name;
        this.area = area;
        this.application = application;
    }

    public Category(Integer id, String name, String area, String application) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.application = application;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id){this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", area='" + area + '\'' +
                ", application='" + application + '\'' +
                '}';
    }
}
