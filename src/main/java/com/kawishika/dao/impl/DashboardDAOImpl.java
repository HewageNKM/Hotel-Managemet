package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.DashboardDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.entity.Reserve;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public ArrayList<Reserve> getDetails() {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<String> data = new ArrayList<>();
        List list = session.createQuery("FROM Reserve where Status = 'Active'").list();
        transaction.commit();
        session.close();
        return (ArrayList<Reserve>) list;
    }

    @Override
    public String getMail(String reserveId) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT Student_Email FROM student left join reserve on student_Student_ID = student.Student_ID WHERE Reserve_ID = ?");
        nativeQuery.setParameter(1, reserveId);
        String email = nativeQuery.uniqueResult().toString();
        transaction.commit();
        session.close();
        return email;
    }

    @Override
    public Double getLineChartData(int i) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT SUM(Total) FROM reserve WHERE MONTH(CheckOut_Date) = ?");
        nativeQuery.setParameter(1, i);
        Double count = 0.0;
        try {
            count = Double.valueOf(nativeQuery.uniqueResult().toString());
            transaction.commit();
            session.close();
        }catch (NullPointerException e){
            transaction.commit();
            session.close();
            return 0.0;
        }
        session.close();
        return count;
    }
}
