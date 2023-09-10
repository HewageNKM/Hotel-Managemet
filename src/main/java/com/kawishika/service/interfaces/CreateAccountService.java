package com.kawishika.service.interfaces;

import com.kawishika.dto.UserDTO;
import com.kawishika.service.SuperService;
import com.kawishika.util.CustomException;

public interface CreateAccountService extends SuperService {
    boolean sendCode(String emailFldText);

    boolean validateCode(Integer integer);

    boolean validatePassword(String password);

    boolean validateUserName(String userName) throws CustomException;

    boolean createAccount(UserDTO dto) throws CustomException;

    boolean validateEmail(String email);
}
