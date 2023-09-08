package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.entity.User;

public interface LoginDAO extends CrudDAO<User> {
    boolean verifyLogin(User user);
}
