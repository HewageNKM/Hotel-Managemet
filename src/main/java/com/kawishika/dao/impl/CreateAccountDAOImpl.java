package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.CreateAccountDAO;
import com.kawishika.entity.User;
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
    public boolean save(User entity) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean checkUserName(String useName) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, useName);
        transaction.commit();
        session.close();
        return user == null;
    }
}
