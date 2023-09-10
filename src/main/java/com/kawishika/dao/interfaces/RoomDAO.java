package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.entity.Room;
import com.kawishika.entity.RoomCategory;
import com.kawishika.util.CustomException;

import java.util.ArrayList;

public interface RoomDAO extends CrudDAO<Room> {
    ArrayList<String> getCategories() throws CustomException;

    String getRoomId(String newValue) throws CustomException;

    String getRoomNumber() throws CustomException;

    boolean validateRoomType(String roomId) throws CustomException;

    boolean validateRoomId(String roomId) throws CustomException;

    RoomCategory getRoomCategoryById(String roomId) throws CustomException;

    boolean isRoomExist(String roomNumber) throws CustomException;

    boolean save(Room room, String roomId) throws CustomException;

    boolean delete(Room entity,String roomId) throws CustomException;
    ArrayList<CustomDTO> getAll() throws CustomException;

    ArrayList<CustomDTO> search(String searchPhrase) throws CustomException;
}
