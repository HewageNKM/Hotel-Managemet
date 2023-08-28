package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.entity.User;

public interface UserDAO extends CrudDAO<User> {
    boolean isUserExist(String userName);

    User getUser(User user);
}
