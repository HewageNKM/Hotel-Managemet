package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.entity.User;
import com.kawishika.util.CustomException;

public interface UserDAO extends CrudDAO<User> {
    boolean isUserExist(String userName) throws CustomException;

    User getUser(User user) throws CustomException;
}
