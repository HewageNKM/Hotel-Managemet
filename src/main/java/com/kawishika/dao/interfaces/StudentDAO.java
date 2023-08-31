package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.entity.Student;

import java.util.List;

public interface StudentDAO extends CrudDAO<Student> {
    boolean isExists(Student student);

    Student getStudent(String id);

    List<Student> searchStudent(String searchPhrase);
}
