package com.kawishika.service.impl;

import com.kawishika.dao.DAOFactory;
import com.kawishika.dao.impl.RoomDAOImpl;
import com.kawishika.dao.interfaces.RoomDAO;
import com.kawishika.dto.CustomDTO;
import com.kawishika.dto.RoomDTO;
import com.kawishika.dto.tm.RoomTM;
import com.kawishika.entity.Room;
import com.kawishika.service.interfaces.RoomService;
import com.kawishika.util.CustomException;
import com.kawishika.util.Regex;
import javafx.scene.control.Button;

import java.util.ArrayList;

import static com.kawishika.dao.DAOFactory.DAOType.ROOM;

public class RoomServiceImpl implements RoomService {
    private final RoomDAO roomDAO = (RoomDAOImpl) DAOFactory.getInstance().getDAO(ROOM);

    @Override
    public ArrayList<String> getCategoryIds() throws CustomException {
        return roomDAO.getCategories();
    }

    @Override
    public String getRoomId(String newValue) throws CustomException {
        return roomDAO.getRoomId(newValue);
    }

    @Override
    public String getRoomNumber() throws CustomException {
        String roomNumber = roomDAO.getRoomNumber();
        if (roomNumber == null) {
            return "R001";
        } else {
            int tempNumber = Integer.parseInt(roomNumber.substring(1));
            tempNumber++;
            if (tempNumber < 10) {
                return "R00" + tempNumber;
            } else if (tempNumber < 100) {
                return "R0" + tempNumber;
            } else {
                return "R" + tempNumber;
            }
        }
    }

    @Override
    public boolean validateRoomType(String roomId) throws CustomException {
        return roomDAO.validateRoomType(roomId);
    }

    @Override
    public boolean validateRoomNumber(String roomNumber) {
        return Regex.validateRoomNumber(roomNumber);
    }

    @Override
    public boolean validateRoomId(String roomId) throws CustomException {
        return roomDAO.validateRoomId(roomId);
    }

    @Override
    public boolean saveRoom(RoomDTO roomDTO, String roomId) throws CustomException {
        return roomDAO.save(new Room(roomDTO.getRoom_Number(), roomDTO.getStatus()), roomId);
    }

    @Override
    public boolean isRoomExist(String roomNumber) throws CustomException {
        return roomDAO.isRoomExist(roomNumber);
    }

    @Override
    public boolean update(RoomDTO roomDTO, String roomId) throws CustomException {
        return roomDAO.update(new Room(roomDTO.getRoom_Number(), roomDTO.getStatus()));
    }

    @Override
    public ArrayList<RoomTM> getAll() throws CustomException {
        ArrayList<CustomDTO> all = roomDAO.getAll();
        ArrayList<RoomTM> roomTMS = new ArrayList<>();
        for (CustomDTO customDTO : all) {
            roomTMS.add(new RoomTM(customDTO.getRoomId(), customDTO.getRoomNumber(), customDTO.getType(), customDTO.getStatus(), new Button("Edit"), new Button("Delete")));
        }
        return roomTMS;
    }

    @Override
    public boolean delete(RoomDTO dto, String roomId) throws CustomException {
        return roomDAO.delete(new Room(dto.getRoom_Number(), dto.getStatus()), roomId);
    }

    @Override
    public ArrayList<RoomTM> search(String searchPhrase) throws CustomException {
        ArrayList<CustomDTO> all = roomDAO.search(searchPhrase);
        ArrayList<RoomTM> roomTMS = new ArrayList<>();
        for (CustomDTO customDTO : all) {
            roomTMS.add(new RoomTM(customDTO.getRoomId(), customDTO.getRoomNumber(), customDTO.getType(), customDTO.getStatus(), new Button("Edit"), new Button("Delete")));
        }
        return roomTMS;
    }
}
