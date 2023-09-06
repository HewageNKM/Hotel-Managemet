package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.RoomDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.entity.Room;
import com.kawishika.entity.RoomCategory;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;

import java.util.ArrayList;

public class RoomDAOImpl implements RoomDAO {
    @Override
    public ArrayList<Room> getAll(ArrayList<Room> entityList) {
        return null;
    }

    @Override
    public boolean update(Room entity) {
        return false;
    }

    @Override
    public boolean delete(Room entity) {
       Session session = SessionConfigureFactory.getInstance().getSession();
       session.beginTransaction();
       Room room = session.get(Room.class, entity.getRoom_Number());
       session.remove(room);
       session.getTransaction().commit();
       session.close();
       return true;
    }

    @Override
    public boolean save(Room entity) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        session.persist(entity);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<String> getCategories() {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        ArrayList<String> categories = (ArrayList<String>) session.createNativeQuery("SELECT Room_Type FROM RoomCategory").list();
        session.getTransaction().commit();
        session.close();
        return categories;
    }

    @Override
    public String getRoomId(String newValue) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        String roomId = (String) session.createNativeQuery("SELECT Room_ID FROM RoomCategory WHERE Room_Type = ?").setParameter(1, newValue).uniqueResult();
        session.getTransaction().commit();
        session.close();
        return roomId;
    }

    @Override
    public String getRoomNumber() {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        Query query = session.createNativeQuery("SELECT Room_Number FROM room ORDER BY Room_Number DESC LIMIT 1");
        String roomNumber = (String) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return roomNumber;
    }

    @Override
    public boolean validateRoomType(String roomId) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        Query query = session.createNativeQuery("SELECT Room_Type FROM roomcategory WHERE Room_Type = ?");
        query.setParameter(1, roomId);
        String roomType = (String) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return roomType != null;
    }

    @Override
    public boolean validateRoomId(String roomId) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        Query query = session.createNativeQuery("SELECT Room_ID FROM roomcategory WHERE Room_ID = ?");
        query.setParameter(1, roomId);
        String roomType = (String) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return roomType != null;
    }

    @Override
    public RoomCategory getRoomCategoryById(String roomId) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        RoomCategory roomCategory = session.get(RoomCategory.class, roomId);
        session.getTransaction().commit();
        session.close();
        return roomCategory;
    }

    @Override
    public boolean isRoomExist(String roomNumber) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        Query query = session.createNativeQuery("SELECT Room_Number FROM room WHERE Room_Number = ?");
        query.setParameter(1, roomNumber);
        String roomType = (String) query.uniqueResult();
        session.getTransaction().commit();
        session.close();
        return roomType != null;
    }

    @Override
    public boolean save(Room room, String roomId) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        RoomCategory roomCategory = session.get(RoomCategory.class, roomId);
        room.setRoomCategory(roomCategory);
        roomCategory.getRooms().add(room);
        session.persist(room);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public boolean update(Room room, String roomId) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        session.beginTransaction();
        RoomCategory roomCategory = session.get(RoomCategory.class, roomId);
        room.setRoomCategory(roomCategory);
        roomCategory.getRooms().add(room);
        session.merge(room);
        session.getTransaction().commit();
        session.close();
        return true;
    }

    @Override
    public ArrayList<CustomDTO> getAll() {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        ArrayList<CustomDTO> ts = new ArrayList<>();
        NativeQuery nativeQuery = session.createNativeQuery("SELECT Room_Number, Status, Room_ID, Room_Type FROM room inner join d24.roomcategory r on room.roomCategory_Room_ID = r.Room_ID");
        ArrayList<Object[]> objects = (ArrayList<Object[]>) nativeQuery.list();
        for (Object[] object : objects) {
            ts.add(new CustomDTO((String) object[0], (String) object[1], (String) object[2], (String) object[3]));
        }
        transaction.commit();
        session.close();
        return ts;
    }
}
