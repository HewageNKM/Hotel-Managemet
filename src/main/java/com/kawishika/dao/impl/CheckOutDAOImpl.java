package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.CheckOutDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.tm.CustomTM;
import com.kawishika.entity.Room;
import com.kawishika.entity.Student;
import com.kawishika.util.CustomException;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class CheckOutDAOImpl implements CheckOutDAO {
    @Override
    public ArrayList getAll(ArrayList entityList) {
        return null;
    }

    @Override
    public boolean update(Object entity) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            CustomTM dto = (CustomTM) entity;
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            Room room = session.get(Room.class, dto.getRoomNumber());
            room.setStatus("Active");
            NativeQuery nativeQuery = session.createNativeQuery("UPDATE reserve SET Status = 'Inactive' WHERE (Reserve_ID = ? or student_Student_ID = ?) and Status = 'Active'");
            nativeQuery.setParameter(1, dto.getReserveId());
            nativeQuery.setParameter(2, dto.getStudentId());
            int i = nativeQuery.executeUpdate();
            session.persist(room);
            transaction.commit();
            return i > 0;
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
    public boolean delete(Object entity) {
        return false;
    }

    @Override
    public boolean save(Object entity) {
        return false;
    }

    @Override
    public CustomDTO getReserveDetails(String id) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            NativeQuery nativeQuery = session.createNativeQuery("SELECT r.Reserve_ID, r.student_Student_ID, r.room_Room_Number, r.Total,r.Payment_Status, r.Reserve_Date, r.CheckOut_Date FROM reserve r WHERE (r.Reserve_ID = ? or r.student_Student_ID = ? or room_Room_Number = ?) and r.Status = 'Active' limit 1");
            nativeQuery.setParameter(1, id);
            nativeQuery.setParameter(2, id);
            nativeQuery.setParameter(3, id);
            List resultList = nativeQuery.getResultList();
            if (resultList.isEmpty()) {
                transaction.commit();
                session.close();
                return null;
            }
            Object[] object = (Object[]) resultList.get(0);
            CustomDTO dto;
            dto = new CustomDTO(object[0].toString(), object[1].toString(), object[2].toString(), Double.parseDouble(object[3].toString()), object[4].toString(), (Date) object[5], (Date) object[6]);
            transaction.commit();
            return dto;
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
    public ArrayList<String> getMail(CustomTM customTM) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            NativeQuery nativeQuery = session.createNativeQuery("SELECT s.Student_Email, s.Student_Name FROM student s WHERE s.Student_ID = ?");
            nativeQuery.setParameter(1, customTM.getStudentId());
            List resultList = nativeQuery.getResultList();
            Object[] object = (Object[]) resultList.get(0);
            ArrayList<String> mail = new ArrayList<>();
            mail.add(object[0].toString());
            mail.add(object[1].toString());
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
            throw new CustomException("Error While Getting Data");
        }finally {
            if (session != null) session.close();
        }
    }
}
