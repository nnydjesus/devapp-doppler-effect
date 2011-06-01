package ar.edu.unq.dopplereffect.repositories;

import java.util.List;

public interface Repository<T> {

    void save(T object);

    void update(T object);

    void delete(T object);

    List<T> searchAll();

    List<T> searchByExample(T object);

    T getByName(String name);

    T getByLikeName(String name);

}
