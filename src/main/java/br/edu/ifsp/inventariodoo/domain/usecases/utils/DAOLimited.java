package br.edu.ifsp.inventariodoo.domain.usecases.utils;

import java.util.List;
import java.util.Optional;

public interface DAOLimited<T, K>{
    K create(T type);
    Optional<T> findOne(K key);
    List<T> findAll();
}
