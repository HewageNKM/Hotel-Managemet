package com.kawishika.dao.interfaces;

import com.kawishika.dao.CrudDAO;
import com.kawishika.entity.User;

public interface CreateAccountDAO extends CrudDAO<User> {
    boolean checkUserName(String useName);
}
