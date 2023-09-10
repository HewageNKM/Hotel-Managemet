package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.entity.Student;
import com.kawishika.util.CustomException;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
    boolean isExists(Student student) throws CustomException;

    Student getStudent(String id) throws CustomException;

    List<Student> searchStudent(String searchPhrase) throws CustomException;
}
