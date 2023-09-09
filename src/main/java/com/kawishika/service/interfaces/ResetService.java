package com.kawishika.service.interfaces;

import com.kawishika.dto.UserDTO;
import com.kawishika.service.SuperService;

public interface ResetService extends SuperService {
    boolean sendCode(String userName);

    boolean checkCode(Integer code);

    boolean resetPassword(UserDTO userDTO);

}
