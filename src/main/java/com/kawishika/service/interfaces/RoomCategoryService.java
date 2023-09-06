package com.kawishika.service.interfaces;

import com.kawishika.dto.RoomCategoryDTO;
import com.kawishika.dto.tm.RoomCategoryTM;
import com.kawishika.entity.RoomCategory;
import com.kawishika.service.SuperService;

import java.util.ArrayList;

public interface RoomCategoryService extends SuperService {
    ArrayList<RoomCategoryTM> getAll();

    boolean validateCost(String cost);

    boolean validateId(String id);

    boolean validateType(String type);

    boolean update(RoomCategoryDTO roomCategoryDTO);

    boolean save(RoomCategoryDTO roomCategoryDTO);

    boolean isExists(String id);

    boolean delete(RoomCategoryDTO roomCategoryDTO);

    ArrayList<RoomCategoryTM> search(String searchFldText);
}
