package com.kawishika.service;

import com.kawishika.service.impl.*;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return (serviceFactory == null) ? (serviceFactory = new ServiceFactory()) : serviceFactory;
    }

    public enum ServiceType {
        USER, STUDENT, CHECKIN, ROOM_CATEGORY, ROOM, CHECKOUT
    }

    public SuperService getService(ServiceType type) {
        switch (type) {
            case USER:
                return new UserServiceImpl();
            case STUDENT:
                return new StudentServiceImpl();
            case CHECKIN:
                return new CheckinServiceImpl();
            case ROOM_CATEGORY:
                return new RoomCategoryServiceImpl();
            case ROOM:
                return new RoomServiceImpl();
            case CHECKOUT:
                return new CheckOutServiceImpl();
            default:
                return null;
        }
    }
}
