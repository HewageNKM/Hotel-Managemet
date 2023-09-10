package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.CreateAccountDAO;
import com.kawishika.entity.User;
import com.kawishika.util.CustomException;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class CreateAccountDAOImpl implements CreateAccountDAO {
    @Override
    public ArrayList<User> getAll(ArrayList<User> entityList) {
        return null;
    }

    @Override
    public boolean update(User entity) {
        return false;
    }

    @Override
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public boolean save(User entity) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
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
    public boolean checkUserName(String useName) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            User user = session.get(User.class, useName);
            transaction.commit();
            return user == null;
        }catch (Exception e){
            if(transaction != null) {
                try {
                    transaction.rollback();
                }catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
           throw new CustomException("Error While Checking User Name");
        }finally {
            if (session != null) session.close();
        }
    }
}
