package com.example.pirmaslab.persistence;

import java.util.List;

public interface GenericDAO<T> {
    T findOne(Long id);
    List<T> getAll();
    void persist(T entity);
    void update(T entity);
    void delete(Long id);
}
