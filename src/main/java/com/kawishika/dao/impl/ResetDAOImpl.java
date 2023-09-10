package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.ResetDAO;
import com.kawishika.entity.User;
import com.kawishika.service.ServiceFactory;
import com.kawishika.util.CustomException;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class ResetDAOImpl implements ResetDAO {
    private Session session = null;
    private Transaction transaction = null;
    @Override
    public ArrayList<User> getAll(ArrayList<User> entityList) {
        return null;
    }
    @Override
    public boolean update(User entity) throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            User user = session.get(User.class, entity.getUserName());
            user.setPassword(entity.getPassword());
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
    public boolean delete(User entity) {
        return false;
    }

    @Override
    public boolean save(User entity) {
        return false;
    }

    @Override
    public ArrayList<String> sendCode(User user) throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            User entity = session.get(User.class, user.getUserName());
            ArrayList<String> arrayList = new ArrayList<>();
            if (entity == null) {
                arrayList.add("false");
                transaction.commit();
                session.close();
                return arrayList;
            }
            arrayList.add("true");
            arrayList.add(entity.getEmail());
            transaction.commit();
            return arrayList;
        }catch (Exception e){
            if(transaction != null) {
                try {
                    transaction.rollback();
                }catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Sending Code");
        }finally {
            if (session != null) session.close();
        }
    }
}
