package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.CheckOutDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.tm.CustomTM;
import com.kawishika.entity.Room;
import com.kawishika.entity.Student;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class CheckOutDAOImpl implements CheckOutDAO {
    @Override
    public ArrayList getAll(ArrayList entityList) {
        return null;
    }

    @Override
    public boolean update(Object entity) {
        CustomTM dto = (CustomTM) entity;
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        Room room = session.get(Room.class, dto.getRoomNumber());
        room.setStatus("Active");
        NativeQuery nativeQuery = session.createNativeQuery("UPDATE reserve SET Status = 'Inactive' WHERE (Reserve_ID = ? or student_Student_ID = ?) and Status = 'Active'");
        nativeQuery.setParameter(1, dto.getReserveId());
        nativeQuery.setParameter(2, dto.getStudentId());
        int i = nativeQuery.executeUpdate();
        session.persist(room);
        session.getTransaction().commit();
        session.close();
        return i > 0;
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
    public CustomDTO getReserveDetails(String id) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT r.Reserve_ID, r.student_Student_ID, r.room_Room_Number, r.Total,r.Payment_Status, r.Reserve_Date, r.CheckOut_Date FROM reserve r WHERE (r.Reserve_ID = ? or r.student_Student_ID = ?) and r.Status = 'Active' limit 1");
        nativeQuery.setParameter(1, id);
        nativeQuery.setParameter(2, id);
        List resultList = nativeQuery.getResultList();
        if (resultList.isEmpty()) {
            return null;
        }
        Object[] object = (Object[]) resultList.get(0);
        CustomDTO dto;
        dto = new CustomDTO(object[0].toString(), object[1].toString(), object[2].toString(), Double.parseDouble(object[3].toString()), object[4].toString(), (Date) object[5], (Date) object[6]);
        session.getTransaction().commit();
        session.close();
        return dto;
    }

    @Override
    public ArrayList<String> getMail(CustomTM customTM) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, customTM.getStudentId());
        ArrayList<String> mail = new ArrayList<>();
        mail.add(student.getStudent_Email());
        mail.add(student.getStudent_Name());
        transaction.commit();
        session.close();
        return mail;
    }
}
