package com.kawishika.dao;

import com.kawishika.dao.impl.*;

public class DAOFactory {
    public static DAOFactory daoFactory;
    private DAOFactory() {
    }
    public static DAOFactory getInstance() {
        return (daoFactory == null) ? (daoFactory = new DAOFactory()) : daoFactory;
    }
    public enum DAOType {
        USER, STUDENT, CHECKIN, ROOM_CATEGORY, ROOM, PAYMENT, DASHBOARD, LOGIN, CHECKOUT
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
            case ROOM:
                return new RoomDAOImpl();
            case CHECKOUT:
                return new CheckOutDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case DASHBOARD:
                return new DashboardDAOImpl();
            case LOGIN:
                return new LoginDAOImpl();
            default:
                return null;
        }
    }
}
