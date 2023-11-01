package br.edu.ifsp.inventariodoo.domain.entities.item;

public class Place {
    private Integer id;
    private Integer number;
    private String block;

    public Place() {
    }

    public Place(Integer number, String block) {
        this(null, number, block);
    }

    public Place(Integer id, Integer number, String block) {
        this.id = id;
        this.number = number;
        this.block = block;
    }
    //Dúvida
    Place warehouse = new Place(1, 12, "Warehouse");

    public Integer getId() {
        return id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getBlock() {
        return block;
    }

    public void setBlock(String block) {
        this.block = block;
    }

    @Override
    public String toString() {
        return "Place{" +
                "id=" + id +
                ", number=" + number +
                ", block='" + block + '\'' +
                '}';
    }
}
