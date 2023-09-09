package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.ResetDAO;
import com.kawishika.entity.User;
import com.kawishika.service.ServiceFactory;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class ResetDAOImpl implements ResetDAO {
    @Override
    public ArrayList<User> getAll(ArrayList<User> entityList) {
        return null;
    }

    @Override
    public boolean update(User entity) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, entity.getUserName());
        user.setPassword(entity.getPassword());
        transaction.commit();
        session.close();
        return true;
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
    public ArrayList<String> sendCode(User user) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        User user1 = session.get(User.class, user.getUserName());
        ArrayList<String> arrayList = new ArrayList<>();
        if (user1 == null) {
            arrayList.add("false");
            transaction.commit();
            session.close();
            return arrayList;
        }
        arrayList.add("true");
        arrayList.add(user1.getEmail());
        transaction.commit();
        session.close();
        return arrayList;
    }
}
