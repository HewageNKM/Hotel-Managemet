package com.kawishika.service.interfaces;

import com.kawishika.dto.RoomCategoryDTO;
import com.kawishika.dto.tm.RoomCategoryTM;
import com.kawishika.entity.RoomCategory;
import com.kawishika.service.SuperService;
import com.kawishika.util.CustomException;

import java.util.ArrayList;

public interface RoomCategoryService extends SuperService {
    ArrayList<RoomCategoryTM> getAll() throws CustomException;

    boolean validateCost(String cost);

    boolean validateId(String id);

    boolean validateType(String type);

    boolean update(RoomCategoryDTO roomCategoryDTO) throws CustomException;

    boolean save(RoomCategoryDTO roomCategoryDTO) throws CustomException;

    boolean isExists(String id) throws CustomException;

    boolean delete(RoomCategoryDTO roomCategoryDTO) throws CustomException;

    ArrayList<RoomCategoryTM> search(String searchFldText) throws CustomException;
}
