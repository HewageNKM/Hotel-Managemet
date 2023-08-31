package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.StudentDAO;
import com.kawishika.entity.Student;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

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
        student.setGender(entity.getGender());
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

    @Override
    public List<Student> searchStudent(String searchPhrase) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        List<Student> studentList = new ArrayList<>();
        Query query = session.createQuery("from Student WHERE Student_ID like : value or Student_Name like: value or Student_Email like : value or BirthDay like: value or Gender like: value or Phone_No like : value or Status like : value");
        query.setParameter("value", searchPhrase + "%");
        studentList.addAll(query.list());
        session.getTransaction().commit();
        session.close();
        return studentList;
    }
}
