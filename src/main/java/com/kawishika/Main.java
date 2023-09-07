package com.kawishika;

import com.kawishika.entity.Room;
import com.kawishika.entity.RoomCategory;
import com.kawishika.util.SessionConfigureFactory;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        Session session = SessionConfigureFactory.getInstance().getSession();
        Transaction transaction = session.beginTransaction();
        Room room = session.get(Room.class, "R001");
        System.out.println(room.getRoom_Number());
        System.out.println(room != null);
        transaction.commit();
        session.close();
    }
}
