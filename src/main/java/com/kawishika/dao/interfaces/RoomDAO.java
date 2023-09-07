package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.entity.Room;
import com.kawishika.entity.RoomCategory;

import java.util.ArrayList;

public interface RoomDAO extends CrudDAO<Room> {
    ArrayList<String> getCategories();

    String getRoomId(String newValue);

    String getRoomNumber();

    boolean validateRoomType(String roomId);

    boolean validateRoomId(String roomId);

    RoomCategory getRoomCategoryById(String roomId);

    boolean isRoomExist(String roomNumber);

    boolean save(Room room, String roomId);

    boolean delete(Room entity,String roomId);
    ArrayList<CustomDTO> getAll();
}
