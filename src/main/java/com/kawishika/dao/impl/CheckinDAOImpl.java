package com.kawishika.dao.impl;

import com.kawishika.dao.interfaces.CheckinDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.entity.Reserve;
import com.kawishika.entity.Room;
import com.kawishika.entity.Student;
import com.kawishika.util.CustomException;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;

import java.util.ArrayList;
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
    public ArrayList<String> getRoomType() throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            ArrayList<String> roomTypes = new ArrayList<>();
            NativeQuery nativeQuery = session.createNativeQuery("SELECT Room_Type from RoomCategory");
            roomTypes.addAll(nativeQuery.list());
            transaction.commit();
            return roomTypes;
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Getting Data");
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public CustomDTO getRoomDetails(String newValue) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            NativeQuery nativeQuery = session.createNativeQuery("SELECT Room_Number,Room_ID,Cost_Per_Week FROM roomcategory left join room on roomcategory.Room_ID = room.roomCategory_Room_ID where room.Status = 'Active' and roomcategory.Room_Type=?");
            nativeQuery.setParameter(1, newValue);
            List<Object[]> list = nativeQuery.list();
            CustomDTO customDTO = new CustomDTO();
            for (Object[] objects : list) {
                customDTO.setRoomNumber((String) objects[0]);
                customDTO.setRoomId((String) objects[1]);
                customDTO.setCost((Double) objects[2]);
            }
            transaction.commit();
            return customDTO;
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Getting Data");
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public ArrayList<String> getStudentId(String newValue) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            ArrayList<String> studentIds = new ArrayList<>();
            NativeQuery nativeQuery = session.createNativeQuery("SELECT Student_ID FROM student where Student_ID like ?");
            nativeQuery.setParameter(1, newValue + "%");
            studentIds.addAll(nativeQuery.list());
            transaction.commit();
            return studentIds;
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Getting Data");
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean checkReserveId(String id) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            NativeQuery nativeQuery = session.createNativeQuery("SELECT Reserve_ID FROM reserve where Reserve_ID = ?");
            nativeQuery.setParameter(1, id);
            List list = nativeQuery.list();
            transaction.commit();
            return !list.isEmpty();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Getting Data");
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public boolean save(Reserve entity, String studentId, String roomNumber) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
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
            return true;
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Saving Data");
        }
    }

    @Override
    public String checkStudentEligibility(String studentId) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            transaction.commit();
            return student.getStatus();
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Getting Data");
        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public String checkReservation(String id) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            List list = session.createNativeQuery("SELECT Status from d24.reserve where student_Student_ID = ? and Status = 'Active'").setParameter(1, id).list();
            for (Object o : list) {
                if (Objects.equals(o, "Active")) {
                    return "Active";
                }
            }
            transaction.commit();
            return "Inactive";
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Getting Data");

        } finally {
            if (session != null) session.close();
        }
    }

    @Override
    public ArrayList<String> getMail(String studentId) throws CustomException {
        Session session = null;
        Transaction transaction = null;
        try {
            session = SessionConfigureFactory.getInstance().getSession();
            transaction = session.beginTransaction();
            Student student = session.get(Student.class, studentId);
            ArrayList<String> mail = new ArrayList<>();
            mail.add(student.getStudent_Email());
            mail.add(student.getStudent_Name());
            transaction.commit();
            return mail;
        } catch (Exception e) {
            if (transaction != null) {
                try {
                    transaction.rollback();
                } catch (Exception ex) {
                    throw new CustomException("Error While Roll Backing");
                }
            }
            throw new CustomException("Error While Getting Data");
        } finally {
            if (session != null) session.close();
        }
    }
}
