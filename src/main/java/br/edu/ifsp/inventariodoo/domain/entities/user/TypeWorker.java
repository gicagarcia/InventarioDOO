package br.edu.ifsp.inventariodoo.domain.entities.user;

public enum TypeWorker {
    WAREHOUSEMAN("Almoxarife"),
    PREMIER("Presidente"),
    PERSON("Pessoa");

    private String label;
    TypeWorker(String label){this.label = label;}

    @Override
    public String toString() {return label;}
}
