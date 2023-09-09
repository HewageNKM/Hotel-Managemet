package com.kawishika.service.interfaces;

import com.kawishika.dto.UserDTO;
import com.kawishika.service.SuperService;

public interface CreateAccountService extends SuperService {
    boolean sendCode(String emailFldText);

    boolean validateCode(Integer integer);

    boolean validatePassword(String password);

    boolean validateUserName(String userName);

    boolean createAccount(UserDTO dto);

    boolean validateEmail(String email);
}
