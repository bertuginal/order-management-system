package com.fmss.repository.repositories;

import com.fmss.repository.impls.GenericRepository;

import java.util.ArrayList;
import java.util.List;

public class GenericRepositoryImpl<T> implements GenericRepository<T> {
    private final List<T> entities = new ArrayList<T>();

    @Override
    public void save(T entity) {
        entities.add(entity);
    }

    @Override
    public void delete(T entity) {
        int index = entities.indexOf(entity);

        if (index != -1) {
            entities.remove(index);
        }
    }

    @Override
    public T findById(T entity) {
        int index = entities.indexOf(entity);
        return (index != -1) ? entities.get(index) : null;
    }

    @Override
    public List<T> findAll() {
        return entities;
    }
}
