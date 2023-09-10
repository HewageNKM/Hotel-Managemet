package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.RoomDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.entity.Room;
import com.kawishika.entity.RoomCategory;
import com.kawishika.util.CustomException;
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
    public boolean update(Room entity) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            Room room = session.get(Room.class, entity.getRoom_Number());
            room.setStatus(entity.getStatus());
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
    public boolean delete(Room entity) {
        return false;
    }

    @Override
    public boolean save(Room entity) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            session.persist(entity);
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
            throw new CustomException("Error While Saving Data");
        }finally {
            if (session != null) session.close();
        }
    }

    @Override
    public ArrayList<String> getCategories() throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            ArrayList<String> categories = (ArrayList<String>) session.createNativeQuery("SELECT Room_Type FROM RoomCategory").list();
            transaction.commit();
            return categories;
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
    public String getRoomId(String newValue) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery("SELECT Room_ID FROM RoomCategory WHERE Room_Type = ?");
            query.setParameter(1, newValue);
            String roomId = (String) query.uniqueResult();
            transaction.commit();
            return roomId;
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
    public String getRoomNumber() throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery("SELECT Room_Number FROM room ORDER BY Room_Number DESC LIMIT 1");
            String roomNumber = (String) query.uniqueResult();
            transaction.commit();
            return roomNumber;
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
    public boolean validateRoomType(String roomId) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery("SELECT Room_Type FROM roomcategory WHERE Room_Type = ?");
            query.setParameter(1, roomId);
            String roomType = (String) query.uniqueResult();
            transaction.commit();
            return roomType != null;
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
    public boolean validateRoomId(String roomId) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery("SELECT Room_ID FROM roomcategory WHERE Room_ID = ?");
            query.setParameter(1, roomId);
            String roomType = (String) query.uniqueResult();
            transaction.commit();
            return roomType != null;
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
    public RoomCategory getRoomCategoryById(String roomId) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            RoomCategory roomCategory = session.get(RoomCategory.class, roomId);
            transaction.commit();
            return roomCategory;
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
    public boolean isRoomExist(String roomNumber) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            Query query = session.createNativeQuery("SELECT Room_Number FROM room WHERE Room_Number = ?");
            query.setParameter(1, roomNumber);
            String roomType = (String) query.uniqueResult();
            transaction.commit();
            return roomType != null;
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
    public boolean save(Room room, String roomId) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            RoomCategory roomCategory = session.get(RoomCategory.class, roomId);
            room.setRoomCategory(roomCategory);
            roomCategory.getRooms().add(room);
            session.persist(room);
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
            throw new CustomException("Error While Saving Data");
        }finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean delete(Room entity, String roomId) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            RoomCategory roomCategory = session.get(RoomCategory.class, roomId);
            entity = session.get(Room.class, entity.getRoom_Number());
            roomCategory.getRooms().remove(entity);
            session.remove(entity);
            session.persist(roomCategory);
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
            throw new CustomException("Error While Deleting Data");
        }finally {
            if (session != null) session.close();
        }
    }

    @Override
    public ArrayList<CustomDTO> getAll() throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            ArrayList<CustomDTO> ts = new ArrayList<>();
            NativeQuery nativeQuery = session.createNativeQuery("SELECT Room_Number, Status, Room_ID, Room_Type FROM room inner join d24.roomcategory r on room.roomCategory_Room_ID = r.Room_ID");
            ArrayList<Object[]> objects = (ArrayList<Object[]>) nativeQuery.list();
            for (Object[] object : objects) {
                ts.add(new CustomDTO((String) object[0], (String) object[1], (String) object[2], (String) object[3]));
            }
            transaction.commit();
            return ts;
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
    public ArrayList<CustomDTO> search(String searchPhrase) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            ArrayList<CustomDTO> ts = new ArrayList<>();
            NativeQuery nativeQuery = session.createNativeQuery("SELECT Room_Number, Status, Room_ID, Room_Type FROM room inner join d24.roomcategory r on room.roomCategory_Room_ID = r.Room_ID where Room_Number like ? or Status like ? or Room_ID like ? or Room_Type like ?");
            nativeQuery.setParameter(1, "%" + searchPhrase + "%");
            nativeQuery.setParameter(2, "%" + searchPhrase + "%");
            nativeQuery.setParameter(3, "%" + searchPhrase + "%");
            nativeQuery.setParameter(4, "%" + searchPhrase + "%");
            ArrayList<Object[]> objects = (ArrayList<Object[]>) nativeQuery.list();
            for (Object[] object : objects) {
                ts.add(new CustomDTO((String) object[0], (String) object[1], (String) object[2], (String) object[3]));
            }
            transaction.commit();
            return ts;
        }catch (Exception e){
            if(transaction != null) {
                try {
                    transaction.rollback();
                }catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Searching Data");
        }finally {
            if (session != null) session.close();
        }
    }
}
