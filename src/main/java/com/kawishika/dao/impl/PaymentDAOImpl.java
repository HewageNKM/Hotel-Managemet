package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.PaymentDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.tm.CustomTM;
import com.kawishika.entity.Reserve;
import com.kawishika.entity.Student;
import com.kawishika.util.CustomException;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public ArrayList<CustomDTO> getAll(ArrayList<CustomDTO> entityList) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            NativeQuery nativeQuery = session.createNativeQuery("SELECT r.Reserve_ID, r.student_Student_ID, r.room_Room_Number, r.Total,r.Payment_Status FROM reserve r");
            List resultList = nativeQuery.getResultList();
            for (Object o : resultList) {
                Object[] object = (Object[]) o;
                entityList.add(new CustomDTO(
                        (String) object[0],
                        (String) object[1],
                        (String) object[2],
                        (Double) object[3],
                        (String) object[4]
                ));
            }
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
    public ArrayList<CustomDTO> search(String searchPhrase) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            NativeQuery nativeQuery = session.createNativeQuery("SELECT r.Reserve_ID, r.student_Student_ID, r.room_Room_Number, r.Total,r.Payment_Status FROM reserve r WHERE Reserve_ID LIKE ? or student_Student_ID LIKE ? or r.Status = ? or r.Payment_Status LIKE ? or r.room_Room_Number LIKE ? or r.Total LIKE ? or CheckOut_Date like ? or Reserve_Date like ?");
            nativeQuery.setParameter(1, "%" + searchPhrase + "%");
            nativeQuery.setParameter(2, "%" + searchPhrase + "%");
            nativeQuery.setParameter(3, "%" + searchPhrase + "%");
            nativeQuery.setParameter(4, "%" + searchPhrase + "%");
            nativeQuery.setParameter(5, "%" + searchPhrase + "%");
            nativeQuery.setParameter(6, "%" + searchPhrase + "%");
            nativeQuery.setParameter(7, "%" + searchPhrase + "%");
            nativeQuery.setParameter(8, "%" + searchPhrase + "%");
            List resultList = nativeQuery.getResultList();
            ArrayList<CustomDTO> entityList = new ArrayList<>();
            for (Object o : resultList) {
                Object[] object = (Object[]) o;
                entityList.add(new CustomDTO(
                        (String) object[0],
                        (String) object[1],
                        (String) object[2],
                        (Double) object[3],
                        (String) object[4]
                ));
            }
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
            throw new CustomException("Error While Roll Backing");
        }finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean update(String reserveId) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            Reserve reserve = session.get(Reserve.class, reserveId);
            reserve.setPayment_Status("Paid");
            session.persist(reserve);
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
    public ArrayList<String> getMail(CustomTM selectedItem) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            NativeQuery nativeQuery = session.createNativeQuery("SELECT s.Student_Email, s.Student_Name FROM student s WHERE Student_ID = ?");
            nativeQuery.setParameter(1, selectedItem.getStudentId());
            List resultList = nativeQuery.getResultList();
            ArrayList<String> mail = new ArrayList<>();
            for (Object o : resultList) {
                Object[] object = (Object[]) o;
                mail.add((String) object[0]);
                mail.add((String) object[1]);
            }
            transaction.commit();
            return mail;
        }catch (Exception e){
            if(transaction != null) {
                try {
                    transaction.rollback();
                }catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While While Getting Data");
        }finally {
            if (session != null) session.close();
        }
    }
}
