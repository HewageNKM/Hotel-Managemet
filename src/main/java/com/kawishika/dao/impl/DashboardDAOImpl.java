package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.DashboardDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;

public class DashboardDAOImpl implements DashboardDAO {
    @Override
    public ArrayList<CustomDTO> getAll(ArrayList<CustomDTO> entityList) {
        return null;
    }

    @Override
    public boolean update(CustomDTO entity) {
        return false;
    }

    @Override
    public boolean delete(CustomDTO entity) {
        return false;
    }

    @Override
    public boolean save(CustomDTO entity) {
        return false;
    }

    @Override
    public ArrayList<Integer> getPieData() {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<Integer> data = new ArrayList<>();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT COUNT(Room_Number) FROM room WHERE Status = 'Not Available'");
        data.add(Integer.parseInt(nativeQuery.uniqueResult().toString()));
        nativeQuery = session.createNativeQuery("SELECT COUNT(Room_Number) FROM room");
        data.add(Integer.parseInt(nativeQuery.uniqueResult().toString()));
        transaction.commit();
        session.close();
        return data;
    }

    @Override
    public ArrayList<CustomDTO> getPaymentData() {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<CustomDTO> data = new ArrayList<>();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT Reserve_Id,student_Student_ID, Payment_Status FROM reserve where Payment_Status='Not Paid'");
        ArrayList<Object[]> list = (ArrayList<Object[]>) nativeQuery.list();
        for (Object[] objects : list) {
            data.add(new CustomDTO(objects[0].toString(), objects[1].toString(), objects[2].toString()));
        }
        transaction.commit();
        session.close();
        return data;
    }
}