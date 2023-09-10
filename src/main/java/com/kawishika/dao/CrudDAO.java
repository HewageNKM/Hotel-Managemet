package com.kawishika.dao;

import com.kawishika.util.CustomException;

import java.util.ArrayList;

public interface CrudDAO<T> extends SuperDAO{
    ArrayList<T> getAll(ArrayList<T> entityList) throws CustomException;
    boolean update(T entity) throws CustomException;
    boolean delete(T entity) throws CustomException;
    boolean save(T entity) throws CustomException;
}
