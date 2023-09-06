package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.entity.RoomCategory;

import java.util.ArrayList;

public interface RoomCategoryDAO extends CrudDAO<RoomCategory> {
    boolean isExists(String id);

    ArrayList<RoomCategory> search(String searchPhrase);
}
