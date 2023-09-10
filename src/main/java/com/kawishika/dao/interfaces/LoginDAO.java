package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.entity.User;
import com.kawishika.util.CustomException;

public interface LoginDAO extends CrudDAO<User> {
    boolean verifyLogin(User user) throws CustomException;
}
