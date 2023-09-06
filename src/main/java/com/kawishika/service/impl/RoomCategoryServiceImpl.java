package com.kawishika.service.impl;

import com.kawishika.dao.DAOFactory;
import com.kawishika.dao.impl.RoomCategoryDAOImpl;
import com.kawishika.dao.interfaces.RoomCategoryDAO;
import com.kawishika.dto.RoomCategoryDTO;
import com.kawishika.dto.tm.RoomCategoryTM;
import com.kawishika.entity.RoomCategory;
import com.kawishika.service.interfaces.RoomCategoryService;
import com.kawishika.util.Regex;
import javafx.scene.control.Button;

import java.util.ArrayList;

public class RoomCategoryServiceImpl implements RoomCategoryService {
    private final RoomCategoryDAO roomCategoryDAO = (RoomCategoryDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.ROOM_CATEGORY);
    @Override
    public ArrayList<RoomCategoryTM> getAll() {
        ArrayList<RoomCategory> all = roomCategoryDAO.getAll(new ArrayList<>());
        ArrayList<RoomCategoryTM> roomCategoryTMS = new ArrayList<>();
        for (RoomCategory roomCategory : all) {
            Button Edit = new Button("Edit");
            Button Delete = new Button("Delete");
            roomCategoryTMS.add(new RoomCategoryTM(roomCategory.getRoom_ID(), roomCategory.getRoom_Type(), roomCategory.getCost_Per_Week(),Edit,Delete));
        }
        return roomCategoryTMS;
    }

    @Override
    public boolean validateCost(String cost) {
        return Regex.validateDoubleValue(cost);
    }

    @Override
    public boolean validateId(String id) {
        return !id.trim().isEmpty();
    }

    @Override
    public boolean validateType(String type) {
        return !type.trim().isEmpty();
    }

    @Override
    public boolean update(RoomCategoryDTO roomCategoryDTO) {
        return roomCategoryDAO.update(new RoomCategory(roomCategoryDTO.getRoom_ID(), roomCategoryDTO.getRoom_Type(), roomCategoryDTO.getCost_Per_Week()));
    }

    @Override
    public boolean save(RoomCategoryDTO roomCategoryDTO) {
        return roomCategoryDAO.save(new RoomCategory(roomCategoryDTO.getRoom_ID(), roomCategoryDTO.getRoom_Type(), roomCategoryDTO.getCost_Per_Week()));
    }

    @Override
    public boolean isExists(String id) {
        return roomCategoryDAO.isExists(id);
    }

    @Override
    public boolean delete(RoomCategoryDTO roomCategoryDTO) {
        return roomCategoryDAO.delete(new RoomCategory(roomCategoryDTO.getRoom_ID(), roomCategoryDTO.getRoom_Type(), roomCategoryDTO.getCost_Per_Week()));
    }

    @Override
    public ArrayList<RoomCategoryTM> search(String searchFldText) {
        ArrayList<RoomCategory> search = roomCategoryDAO.search(searchFldText);
        ArrayList<RoomCategoryTM> roomCategoryTMS = new ArrayList<>();
        for (RoomCategory roomCategory : search) {
            Button Edit = new Button("Edit");
            Button Delete = new Button("Delete");
            roomCategoryTMS.add(new RoomCategoryTM(roomCategory.getRoom_ID(), roomCategory.getRoom_Type(), roomCategory.getCost_Per_Week(),Edit,Delete));
        }
        return roomCategoryTMS;
    }
}
