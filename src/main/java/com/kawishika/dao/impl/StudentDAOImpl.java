package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.StudentDAO;
import com.kawishika.entity.Student;
import com.kawishika.util.CustomException;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class StudentDAOImpl implements StudentDAO {
    private Session session = null;
    private Transaction transaction = null;
    @Override
    public ArrayList<Student> getAll(ArrayList<Student> entityList) throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            entityList.addAll(session.createQuery("FROM Student").list());
            transaction.commit();
            return entityList;
        }catch (Exception e){
            if(transaction != null) {
                try {
                    transaction.rollback();
                }catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Getting Data");
        }finally {
            if (session != null) session.close();
        }

    }

    @Override
    public boolean update(Student entity) throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, entity.getStudent_ID());
            student.setStudent_Name(entity.getStudent_Name());
            student.setBirthDay(entity.getBirthDay());
            student.setStudent_Email(entity.getStudent_Email());
            student.setPhone_No(entity.getPhone_No());
            student.setGender(entity.getGender());
            student.setStatus(entity.getStatus());
            transaction.commit();
            return true;
        }catch (Exception e){
            if(transaction != null) {
                try {
                    transaction.rollback();
                }catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Updating Data");
        }finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean delete(Student entity) throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, entity.getStudent_ID());
            session.remove(student);
            transaction.commit();
            return true;
        }catch (Exception e){
            if(transaction != null) {
                try {
                    transaction.rollback();
                }catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Deleting Data");
        }finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean save(Student entity) throws CustomException {
        try{
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            session.persist(entity);
            transaction.commit();
            return true;
        }catch (Exception e){
            if(transaction != null) {
                try {
                    transaction.rollback();
                }catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Saving Data");
        }finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean isExists(Student student) throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            Student entity = session.get(Student.class, student.getStudent_ID());
            transaction.commit();
            return entity != null;
        }catch (Exception e){
            if(transaction != null) {
                try {
                    transaction.rollback();
                }catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Checking Data");
        }finally {
            if (session != null) session.close();
        }
    }

    @Override
    public Student getStudent(String id) throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, id);
            transaction.commit();
            return student;
        }catch (Exception e){
            if(transaction != null) {
                try {
                    transaction.rollback();
                }catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Getting Data");
        }finally {
            if (session != null) session.close();
        }
    }

    @Override
    public List<Student> searchStudent(String searchPhrase) throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            List<Student> studentList = new ArrayList<>();
            Query query = session.createQuery("from Student WHERE Student_ID like : value or Student_Name like: value or Student_Email like : value or BirthDay like: value or Gender like: value or Phone_No like : value or Status like : value");
            query.setParameter("value", searchPhrase + "%");
            studentList.addAll(query.list());
            transaction.commit();
            session.close();
            return studentList;
        }catch (Exception e){
            if(transaction != null) {
                try {
                    transaction.rollback();
                }catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Searching Data");
        }finally {
            if (session != null) session.close();
        }
    }
}
