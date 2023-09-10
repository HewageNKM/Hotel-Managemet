package com.kawishika.service.interfaces;

import com.kawishika.dto.RoomDTO;
import com.kawishika.dto.tm.RoomTM;
import com.kawishika.service.SuperService;
import com.kawishika.util.CustomException;

import java.util.ArrayList;

public interface RoomService extends SuperService {
    ArrayList<String> getCategoryIds() throws CustomException;
    String getRoomId(String newValue) throws CustomException;
    String getRoomNumber() throws CustomException;

    boolean validateRoomType(String roomId) throws CustomException;

    boolean validateRoomNumber(String roomNumber);

    boolean validateRoomId(String roomId) throws CustomException;

    boolean saveRoom(RoomDTO roomDTO, String roomId) throws CustomException;

    boolean isRoomExist(String roomNumber) throws CustomException;

    boolean update(RoomDTO roomDTO, String roomId) throws CustomException;

    ArrayList<RoomTM> getAll() throws CustomException;

    boolean delete(RoomDTO dto,String roomId) throws CustomException;

    ArrayList<RoomTM> search(String searchPhrase) throws CustomException;
}
