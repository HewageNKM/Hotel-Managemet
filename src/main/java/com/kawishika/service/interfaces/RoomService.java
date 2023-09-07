package com.kawishika.service.interfaces;

import com.kawishika.dto.RoomDTO;
import com.kawishika.dto.tm.RoomTM;
import com.kawishika.service.SuperService;

import java.util.ArrayList;

public interface RoomService extends SuperService {
    ArrayList<String> getCategoryIds();
    String getRoomId(String newValue);
    String getRoomNumber();

    boolean validateRoomType(String roomId);

    boolean validateRoomNumber(String roomNumber);

    boolean validateRoomId(String roomId);

    boolean saveRoom(RoomDTO roomDTO, String roomId);

    boolean isRoomExist(String roomNumber);

    boolean update(RoomDTO roomDTO, String roomId);

    ArrayList<RoomTM> getAll();

    boolean delete(RoomDTO dto,String roomId);

}
