package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.CheckinDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.ReserveDTO;
import com.kawishika.entity.Reserve;
import com.kawishika.entity.Room;
import com.kawishika.entity.Student;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

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

    @Override
    public CustomDTO getRoomDetails(String newValue) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT Room_Number,Room_ID,Cost_Per_Week FROM roomcategory left join room on roomcategory.Room_ID = room.roomCategory_Room_ID where room.Status = 'Active' and roomcategory.Room_Type=?");
        List<Object[]> list = nativeQuery.setParameter(1, newValue).list();
        CustomDTO customDTO = new CustomDTO();
        for (Object[] objects : list) {
            customDTO.setRoomNumber((String) objects[0]);
            customDTO.setRoomId((String) objects[1]);
            customDTO.setCost((Double) objects[2]);
        }
        transaction.commit();
        session.close();
        return customDTO;
    }

    @Override
    public ArrayList<String> getStudentId(String newValue) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT Student_ID FROM student where Student_ID like ?");
        nativeQuery.setParameter(1, newValue + "%");
        ArrayList<String> list = (ArrayList<String>) nativeQuery.list();
        System.out.println(Arrays.toString(list.toArray()));
        transaction.commit();
        session.close();
        return list;
    }

    @Override
    public boolean checkReserveId(String id) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Reserve reserve = session.get(Reserve.class, id);
        transaction.commit();
        session.close();
        return reserve == null;
    }

    @Override
    public boolean save(Reserve entity, String studentId, String roomNumber) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, studentId);
        Room room = session.get(Room.class, roomNumber);
        room.setStatus("Not Available");
        entity.setStudent(student);
        entity.setRoom(room);
        student.getReserves().add(entity);
        room.getReserves().add(entity);
        session.persist(entity);
        session.persist(student);
        session.persist(room);
        transaction.commit();
        session.close();
        return true;
    }

    @Override
    public String checkStudentEligibility(String studentId) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Student student = session.get(Student.class, studentId);
        transaction.commit();
        session.close();
        return student.getStatus();
    }

    @Override
    public String checkReservation(String id) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        List list = session.createNativeQuery("SELECT Status from d24.reserve where student_Student_ID = ? and Status = 'Active'").setParameter(1, id).list();
        for (Object o : list) {
            if (Objects.equals(o, "Active")) {
                return "Active";
            }
        }
        transaction.commit();
        session.close();
        return "Inactive";
    }
}
