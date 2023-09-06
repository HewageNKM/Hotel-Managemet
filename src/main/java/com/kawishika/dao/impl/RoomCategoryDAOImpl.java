package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.RoomCategoryDAO;
import com.kawishika.entity.RoomCategory;
import com.kawishika.entity.Student;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class RoomCategoryDAOImpl implements RoomCategoryDAO{
    @Override
    public ArrayList<RoomCategory> getAll(ArrayList<RoomCategory> entityList) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        entityList.addAll(session.createQuery("from RoomCategory").list());
        transaction.commit();
        session.close();
        return entityList;
    }

    @Override
    public boolean update(RoomCategory entity) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        RoomCategory roomCategory = session.get(RoomCategory.class, entity.getRoom_ID());
        roomCategory.setRoom_Type(entity.getRoom_Type());
        roomCategory.setCost_Per_Week(entity.getCost_Per_Week());
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean delete(RoomCategory entity) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        RoomCategory roomCategory = session.get(RoomCategory.class, entity.getRoom_ID());
        session.remove(roomCategory);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean save(RoomCategory entity) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(entity);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public boolean isExists(String id) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from RoomCategory where Room_ID = :id");
        query.setParameter("id", id);
        List list = query.list();
        transaction.commit();
        session.close();
        return list.size() > 0;
    }

    @Override
    public ArrayList<RoomCategory> search(String searchPhrase) {
        ArrayList<RoomCategory> roomCategories = new ArrayList<>();
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Query query = session.createQuery("from RoomCategory WHERE Room_ID LIKE : value or Room_Type like :value");
        query.setParameter("value", searchPhrase + "%");
        roomCategories.addAll(query.list());
        transaction.commit();
        session.close();
        return roomCategories;
    }
}
