package com.kawishika.dao;

import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    ArrayList<T> getAll(ArrayList<T> entityList);
    boolean update(T entity);
    boolean delete(T entity);
    boolean save(T entity);
}
