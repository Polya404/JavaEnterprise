package org.application.dao;

import java.util.List;

public interface DAO<T> {

    T findById(long id);

    List<T> findAll();

    void add(T entity);

    void updateById(T entity, long id);

    void deleteById(long id);
}
