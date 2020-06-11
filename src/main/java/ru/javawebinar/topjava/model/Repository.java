package ru.javawebinar.topjava.model;

import java.util.List;
import java.util.Optional;

public interface Repository<T> {
    void add(T entity);

    void update(T entity);

    List<T> getAll();

    Optional<T> getById(int id);

    void remove(int id);
}
