package com.kawishika.service.interfaces;

import com.kawishika.dto.UserDTO;
import com.kawishika.service.SuperService;
import com.kawishika.util.CustomException;

public interface LoginService extends SuperService {
    boolean verifyLogin(UserDTO userDTO) throws CustomException;
}
