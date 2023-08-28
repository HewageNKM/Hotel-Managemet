package com.kawishika.service.interfaces;

import com.kawishika.dto.UserDTO;
import com.kawishika.dto.tm.UserTM;
import com.kawishika.service.SuperService;

import java.util.ArrayList;

public interface UserService extends SuperService {
    boolean validateEmail(String email);

    boolean validatePassword(String password);

    boolean validateUserName(String userName);

    boolean isUserExist(String userName);

    boolean update(UserDTO userDTO);

    boolean save(UserDTO userDTO);

    ArrayList<UserTM> getAll();

    boolean delete(UserDTO userDTO);

    UserDTO getUser(String userName);
}
