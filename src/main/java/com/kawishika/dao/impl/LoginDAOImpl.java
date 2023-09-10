package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.LoginDAO;
import com.kawishika.entity.User;
import com.kawishika.util.CustomException;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public class LoginDAOImpl implements LoginDAO {
    private Session session = null;
    private Transaction transaction = null;
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
    public boolean verifyLogin(User user) throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            User entity = session.get(User.class, user.getUserName());
            if (entity == null) {
                transaction.commit();
                session.close();
                return false;
            }else if (!entity.getPassword().equals(user.getPassword())){
                transaction.commit();
                session.close();
                return false;
            }else if (entity.getStatus().equals("Inactive")){
                transaction.commit();
                session.close();
                return false;
            }else {
                transaction.commit();
                session.close();
                return true;
            }
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
            if (session != null) {
                session.close();
                session=null;
            }
        }
    }
}
