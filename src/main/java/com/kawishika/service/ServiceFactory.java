package com.kawishika.service;

import com.kawishika.service.impl.UserServiceImpl;

public class ServiceFactory {
    private static ServiceFactory serviceFactory;

    private ServiceFactory() {
    }

    public static ServiceFactory getInstance() {
        return (serviceFactory == null) ? (serviceFactory = new ServiceFactory()) : serviceFactory;
    }

    public enum ServiceType {
        USER
    }

    public SuperService getService(ServiceType type) {
        switch (type) {
            case USER:
                return new UserServiceImpl();
            default:
                return null;
        }
    }
}
