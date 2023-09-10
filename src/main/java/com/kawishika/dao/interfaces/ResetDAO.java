package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.entity.User;
import com.kawishika.util.CustomException;

import java.util.ArrayList;

public interface ResetDAO extends CrudDAO<User> {
    ArrayList<String> sendCode(User user) throws CustomException;
}
