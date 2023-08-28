package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.UserDAO;
import com.kawishika.entity.User;
import com.kawishika.util.Hashing;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionDelegatorBaseImpl;

import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {
    @Override
    public ArrayList<User> getAll(ArrayList<User> entityList) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        entityList.addAll(session.createQuery("FROM User").list());
        session.getTransaction().commit();
        session.close();
        for (User user:entityList) {
            user.setUserName(Hashing.getHash(user.getUserName()));
            user.setPassword(Hashing.getHash(user.getPassword()));
            user.setEmail(Hashing.getHash(user.getEmail()));
        }
        return entityList;
    }

    @Override
    public boolean update(User entity) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        User user = session.get(User.class, entity.getUserName());
        user.setPassword(entity.getPassword());
        user.setStatus(entity.getStatus());
        user.setEmail(entity.getEmail());
        user.setUserName(entity.getUserName());
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(User entity){
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        User user = session.get(User.class, entity.getUserName());
        session.remove(user);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean save(User entity) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean isUserExist(String userName) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        User user = session.get(User.class, userName);
        session.getTransaction().commit();
        session.close();
        return user != null;
    }

    @Override
    public User getUser(User entity) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        User user = session.get(User.class, entity.getUserName());
        session.getTransaction().commit();
        session.close();
        return user;
    }
}
