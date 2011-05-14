package ar.edu.unq.dopplereffect.repository;

import java.util.List;

public interface Repository<T> {

    void save(T stock);

    void update(T stock);

    void delete(T stock);

    List<T> searchAll();

    T getByName(String name);
}
