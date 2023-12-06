package br.edu.ifsp.inventariodoo.domain.entities.user;

import java.util.Arrays;

public enum TypeWorker {
    WAREHOUSEMAN("Almoxarife"),
    PREMIER("Presidente"),
    PERSON("Pessoa");

    private String label;
    TypeWorker(String label){this.label = label;}

    @Override
    public String toString() {return label;}

    public static TypeWorker toEnum(String value){
        return Arrays.stream(TypeWorker.values())
                .filter(c -> value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
