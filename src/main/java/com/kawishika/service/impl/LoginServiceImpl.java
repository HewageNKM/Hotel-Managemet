package com.kawishika.service.impl;

import com.kawishika.dao.DAOFactory;
import com.kawishika.dao.impl.LoginDAOImpl;
import com.kawishika.dao.interfaces.LoginDAO;
import com.kawishika.dto.UserDTO;
import com.kawishika.entity.User;
import com.kawishika.service.interfaces.LoginService;
import com.kawishika.util.Hashing;

public class LoginServiceImpl implements LoginService {
    private final LoginDAO loginDAO  = (LoginDAOImpl) DAOFactory.getInstance().getDAO(DAOFactory.DAOType.LOGIN);
    @Override
    public boolean verifyLogin(UserDTO userDTO) {
        return loginDAO.verifyLogin(new User(Hashing.getHash(userDTO.getUserName()), Hashing.getHash(userDTO.getPassword())));
    }
}