package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.entity.RoomCategory;
import com.kawishika.util.CustomException;

import java.util.ArrayList;

public interface RoomCategoryDAO extends CrudDAO<RoomCategory> {
    boolean isExists(String id) throws CustomException;

    ArrayList<RoomCategory> search(String searchPhrase) throws CustomException;
}
