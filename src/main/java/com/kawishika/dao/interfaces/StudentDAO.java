package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.entity.Student;

public interface StudentDAO extends CrudDAO<Student> {
    boolean isExists(Student student);

    Student getStudent(String id);
}
