package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.entity.Reserve;

import java.util.ArrayList;

public interface CheckinDAO extends CrudDAO<Reserve> {
    ArrayList<String> getRoomType();
}
