package com.kawishika.service.impl;

import com.kawishika.dao.DAOFactory;
import com.kawishika.dao.impl.UserDAOImpl;
import com.kawishika.dao.interfaces.UserDAO;
import com.kawishika.dto.UserDTO;
import com.kawishika.dto.tm.UserTM;
import com.kawishika.entity.User;
import com.kawishika.service.interfaces.UserService;
import com.kawishika.util.Regex;

import java.util.ArrayList;

import static com.kawishika.dao.DAOFactory.DAOType.USER;

public class UserServiceImpl implements UserService {
    private final UserDAO userDAO = (UserDAOImpl) DAOFactory.getInstance().getDAO(USER);

    @Override
    public boolean validateEmail(String email) {
        return Regex.validateEmail(email);
    }

    @Override
    public boolean validatePassword(String password) {
        return Regex.validatePassword(password);
    }

    @Override
    public boolean validateUserName(String userName) {
        return Regex.validateUserName(userName);
    }

    @Override
    public boolean isUserExist(String userName) {
        return userDAO.isUserExist(userName);
    }

    @Override
    public boolean update(UserDTO userDTO) {
        return userDAO.update(new User(userDTO.getUserName().toLowerCase(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getStatus()));
    }

    @Override
    public boolean save(UserDTO userDTO) {
        return userDAO.save(new User(userDTO.getUserName().toLowerCase(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getStatus()));
    }

    @Override
    public ArrayList<UserTM> getAll() {
        ArrayList<User> all = userDAO.getAll(new ArrayList<>());
        ArrayList<UserTM> userTMS = new ArrayList<>();
        for (User user : all) {
            userTMS.add(new UserTM(user.getUserName(), user.getPassword(), user.getEmail(), user.getStatus()));
        }
        return userTMS;
    }

    @Override
    public boolean delete(UserDTO userDTO) {
        return userDAO.delete(new User(userDTO.getUserName(), userDTO.getPassword(), userDTO.getEmail(), userDTO.getStatus()));
    }

    @Override
    public UserDTO getUser(String userName) {
        User user = userDAO.getUser(new User(userName, null, null, null));
        if (user == null) {
            return null;
        }
        return new UserDTO(user.getUserName(), user.getPassword(), user.getEmail(), user.getStatus());
    }
}
