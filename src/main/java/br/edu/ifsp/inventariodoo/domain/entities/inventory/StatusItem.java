package br.edu.ifsp.inventariodoo.domain.entities.inventory;

import br.edu.ifsp.inventariodoo.domain.entities.user.TypeWorker;

import java.util.Arrays;

public enum StatusItem {
    NEW,
    USED,
    DAMAGED,
    ABSENT,
    UNUSABLE;

    public static StatusItem toEnum(String value){
        return Arrays.stream(StatusItem.values())
                .filter(c -> value.equals(c.toString()))
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }
}
