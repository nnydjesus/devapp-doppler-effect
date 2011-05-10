package ar.edu.unq.dopplereffect.repositories;

import java.util.List;

public interface Repository<T> {

    void save(T object);

    void update(T object);

    void delete(T object);

    List<T> searchAll();
}
