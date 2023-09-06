package com.kawishika.dao;

import com.kawishika.dao.impl.CheckinDAOImpl;
import com.kawishika.dao.impl.RoomCategoryDAOImpl;
import com.kawishika.dao.impl.StudentDAOImpl;
import com.kawishika.dao.impl.UserDAOImpl;

public class DAOFactory {
    public static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }
    public enum DAOType {
        USER, STUDENT, CHECKIN, ROOM_CATEGORY
    }
    public SuperDAO getDAO(DAOType type) {
        switch (type) {
            case USER:
                return new UserDAOImpl();
            case STUDENT:
                return new StudentDAOImpl();
            case CHECKIN:
                return new CheckinDAOImpl();
            case ROOM_CATEGORY:
                return new RoomCategoryDAOImpl();
            default:
                return null;
        }
    }
}
