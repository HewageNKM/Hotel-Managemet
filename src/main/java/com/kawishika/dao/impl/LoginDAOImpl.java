package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.LoginDAO;
import com.kawishika.entity.User;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class LoginDAOImpl implements LoginDAO {
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
        return false;
    }

    @Override
    public boolean verifyLogin(User user) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        List resultList = session.createNativeQuery("SELECT * FROM user WHERE UserName = ? AND Password = ?")
                .setParameter(1, user.getUserName())
                .setParameter(2, user.getPassword())
                .getResultList();
        if (resultList.isEmpty()) {
            return false;
        }
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
