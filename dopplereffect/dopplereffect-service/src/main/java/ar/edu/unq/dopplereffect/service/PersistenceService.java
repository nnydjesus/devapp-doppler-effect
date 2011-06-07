package ar.edu.unq.dopplereffect.service;

import java.util.List;

public interface PersistenceService<T> extends Service {

    void save(T object);

    void update(T object);

    void delete(T object);

    List<T> searchAll();

    List<T> searchByExample(T object);

    T getByName(String name);

}