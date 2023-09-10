package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.UserDAO;
import com.kawishika.entity.User;
import com.kawishika.util.CustomException;
import com.kawishika.util.Hashing;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class UserDAOImpl implements UserDAO {

    @Override
    public ArrayList<User> getAll(ArrayList<User> entityList) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            entityList.addAll(session.createQuery("FROM User").list());
            transaction.commit();
            for (User user : entityList) {
                user.setEmail(Hashing.getHash(user.getEmail()));
            }
            return entityList;
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Getting Data");
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean update(User entity) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            User user = session.get(User.class, entity.getUserName());
            user.setPassword(entity.getPassword());
            user.setStatus(entity.getStatus());
            user.setEmail(entity.getEmail());
            user.setUserName(entity.getUserName());
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Updating Data");
        } finally {
            if (session != null) session.close();
        }

    }

    @Override
    public boolean delete(User entity) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            User user = session.get(User.class, entity.getUserName());
            session.remove(user);
            transaction.commit();
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Deleting Data");
        } finally {
            if (session != null) session.close();
        }

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
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Saving Data");
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean isUserExist(String userName) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            User user = session.get(User.class, userName);
            transaction.commit();
            return user != null;
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Getting Data");
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public User getUser(User entity) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            User user = session.get(User.class, entity.getUserName());
            transaction.commit();
            return user;
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Getting User");
        } finally {
            if (session != null) session.close();
        }
    }
}
