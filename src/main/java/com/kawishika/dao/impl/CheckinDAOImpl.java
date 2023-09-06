package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.CheckinDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.entity.Reserve;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;

public class CheckinDAOImpl implements CheckinDAO {
    @Override
    public ArrayList<Reserve> getAll(ArrayList<Reserve> entityList) {
        return null;
    }

    @Override
    public boolean update(Reserve entity) {
        return false;
    }

    @Override
    public boolean delete(Reserve entity) {
        return false;
    }

    @Override
    public boolean save(Reserve entity) {
        return false;
    }

    @Override
    public ArrayList<String> getRoomType() {
        ArrayList<String> roomTypes = new ArrayList<>();
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        roomTypes.addAll(session.createQuery("SELECT Room_Type from RoomCategory").list());
        transaction.commit();
        session.close();
        return roomTypes;
    }
}
