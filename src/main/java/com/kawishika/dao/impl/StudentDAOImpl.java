package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.StudentDAO;
import com.kawishika.entity.Student;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;

import java.util.ArrayList;

public class StudentDAOImpl implements StudentDAO {
    @Override
    public ArrayList<Student> getAll(ArrayList<Student> entityList) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        entityList.addAll(session.createQuery("FROM Student").list());
        session.getTransaction().commit();
        session.close();
        return entityList;

    }

    @Override
    public boolean update(Student entity) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        Student student = session.get(Student.class, entity.getStudent_ID());
        student.setStudent_Name(entity.getStudent_Name());
        student.setBirthDay(entity.getBirthDay());
        student.setStudent_Email(entity.getStudent_Email());
        student.setPhone_No(entity.getPhone_No());
        student.setStatus(entity.getStatus());
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(Student entity) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        Student student = session.get(Student.class, entity.getStudent_ID());
        session.remove(student);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean save(Student entity) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean isExists(Student student) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        Student student1 = session.get(Student.class, student.getStudent_ID());
        session.getTransaction().commit();
        session.close();
        return student1 != null;
    }

    @Override
    public Student getStudent(String id) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        Student student = session.get(Student.class, id);
        session.getTransaction().commit();
        session.close();
        return student;
    }
}
