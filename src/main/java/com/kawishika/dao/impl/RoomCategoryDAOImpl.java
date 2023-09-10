package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.RoomCategoryDAO;
import com.kawishika.entity.RoomCategory;
import com.kawishika.util.CustomException;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RoomCategoryDAOImpl implements RoomCategoryDAO{
    private Session session = null;
    private Transaction transaction = null;
    @Override
    public ArrayList<RoomCategory> getAll(ArrayList<RoomCategory> entityList) throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            entityList.addAll(session.createQuery("FROM RoomCategory").list());
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
    public boolean update(RoomCategory entity) throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            RoomCategory roomCategory = session.get(RoomCategory.class, entity.getRoom_ID());
            roomCategory.setRoom_Type(entity.getRoom_Type());
            roomCategory.setCost_Per_Week(entity.getCost_Per_Week());
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
    public boolean delete(RoomCategory entity) throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            RoomCategory roomCategory = session.get(RoomCategory.class, entity.getRoom_ID());
            session.remove(roomCategory);
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
    public boolean save(RoomCategory entity) throws CustomException {
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
    public boolean isExists(String id) throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            RoomCategory roomCategory = session.get(RoomCategory.class, id);
            transaction.commit();
            return roomCategory != null;
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
    public ArrayList<RoomCategory> search(String searchPhrase) throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            List<RoomCategory> roomCategories = new ArrayList<>();
            Query query = session.createQuery("from RoomCategory WHERE Room_ID LIKE : value or Room_Type like :value");
            query.setParameter("value", searchPhrase + "%");
            roomCategories.addAll(query.list());
            transaction.commit();
            return (ArrayList<RoomCategory>) roomCategories;
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
