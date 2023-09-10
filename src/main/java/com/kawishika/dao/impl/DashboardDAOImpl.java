package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.DashboardDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.entity.Reserve;
import com.kawishika.util.CustomException;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class DashboardDAOImpl implements DashboardDAO {
    private Session session = null;
    private Transaction transaction = null;
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
    public ArrayList<Integer> getPieData() throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            ArrayList<Integer> data = new ArrayList<>();
            NativeQuery nativeQuery = session.createNativeQuery("SELECT COUNT(Room_Number) FROM room WHERE Status = 'Not Available'");
            data.add(Integer.parseInt(nativeQuery.uniqueResult().toString()));
            nativeQuery = session.createNativeQuery("SELECT COUNT(Room_Number) FROM room");
            data.add(Integer.parseInt(nativeQuery.uniqueResult().toString()));
            transaction.commit();
            return data;
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
    public ArrayList<CustomDTO> getPaymentData() throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            ArrayList<CustomDTO> data = new ArrayList<>();
            NativeQuery nativeQuery = session.createNativeQuery("SELECT r.Reserve_ID, r.student_Student_ID, r.Payment_Status FROM reserve r where Payment_Status='Not Paid'");
            List resultList = nativeQuery.getResultList();
            for (Object o : resultList) {
                Object[] object = (Object[]) o;
                data.add(new CustomDTO(
                        (String) object[0],
                        (String) object[1],
                        (String) object[2]
                ));
            }
            transaction.commit();
            return data;
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
    public ArrayList<Reserve> getDetails() throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            ArrayList<String> data = new ArrayList<>();
            List list = session.createQuery("FROM Reserve where Status = 'Active'").list();
            transaction.commit();
            transaction.commit();
            return list == null ? null : (ArrayList<Reserve>) list;
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
    public String getMail(String reserveId) throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            NativeQuery nativeQuery = session.createNativeQuery("SELECT Student_Email FROM student left join reserve on student_Student_ID = student.Student_ID WHERE Reserve_ID = ?");
            nativeQuery.setParameter(1, reserveId);
            String email = nativeQuery.uniqueResult().toString();
            transaction.commit();
            return email;
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
    public Double getLineChartData(int i) throws CustomException {
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            NativeQuery nativeQuery = session.createNativeQuery("SELECT SUM(Total) FROM reserve WHERE MONTH(CheckOut_Date) = ?");
            nativeQuery.setParameter(1, i);
            Double count = 0.0;
            try {
                count = Double.valueOf(nativeQuery.uniqueResult().toString());
                transaction.commit();
                return count;
            }catch (NullPointerException e){
                transaction.commit();
                return 0.0;
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
            if (session != null) session.close();
        }
    }
}
