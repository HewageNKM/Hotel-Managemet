package com.kawishika.service.interfaces;

import com.kawishika.dto.UserDTO;
import com.kawishika.service.SuperService;

public interface LoginService extends SuperService {
    boolean verifyLogin(UserDTO userDTO);
}
