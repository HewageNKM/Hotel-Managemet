package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.LoginDAO;
import com.kawishika.entity.User;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

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
        Transaction transaction = session.beginTransaction();
        User user1 = session.get(User.class, user.getUserName());
        if (user1 == null) {
            transaction.commit();
            session.close();
            return false;
        }else if (!user1.getPassword().equals(user.getPassword())){
            transaction.commit();
            session.close();
            return false;
        }else if (user1.getStatus().equals("Inactive")){
            transaction.commit();
            session.close();
            return false;
        }else {
            transaction.commit();
            session.close();
            return true;
        }
    }
}
