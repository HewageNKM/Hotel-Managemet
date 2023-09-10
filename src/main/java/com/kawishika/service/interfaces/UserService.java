package com.kawishika.service.interfaces;

import com.kawishika.dto.UserDTO;
import com.kawishika.dto.tm.UserTM;
import com.kawishika.service.SuperService;
import com.kawishika.util.CustomException;

import java.util.ArrayList;

public interface UserService extends SuperService {
    boolean validateEmail(String email);

    boolean validatePassword(String password);

    boolean validateUserName(String userName);

    boolean isUserExist(String userName) throws CustomException;

    boolean update(UserDTO userDTO) throws CustomException;

    boolean save(UserDTO userDTO) throws CustomException;

    ArrayList<UserTM> getAll() throws CustomException;

    boolean delete(UserDTO userDTO) throws CustomException;

    UserDTO getUser(String userName) throws CustomException;
}
