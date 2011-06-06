package ar.edu.unq.dopplereffect.service;

import java.util.List;
/**
 * 
 * XXX: interface Service en model ??  que funcion cumple?
 */
public interface Service<T> {

    void save(T object);

    void update(T object);

    void delete(T object);

    List<T> searchAll();

    List<T> searchByExample(T object);

    T getByName(String name);

}