package com.kawishika.util;

import com.kawishika.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.Properties;

public class SessionConfigureFactory {
    private static SessionConfigureFactory sessionConfigureFactory;
    private final SessionFactory sessionFactory;

    private SessionConfigureFactory() {
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/d24");
        properties.setProperty("hibernate.connection.username", "kawishika");
        properties.setProperty("hibernate.connection.password", "Uni12code$");
        properties.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        properties.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        properties.setProperty("hibernate.show_sql", "true");
        properties.setProperty("hibernate.hbm2ddl.auto", "update");
        sessionFactory = new Configuration()
                .addProperties(properties)
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Reserve.class)
                .addAnnotatedClass(RoomCategory.class)
                .addAnnotatedClass(Room.class)
                .buildSessionFactory();
    }

    public static SessionConfigureFactory getInstance() {
        return (sessionConfigureFactory == null) ? (sessionConfigureFactory = new SessionConfigureFactory()) : sessionConfigureFactory;
    }

    public Session getSession() {
        return sessionFactory.openSession();

    }
}
