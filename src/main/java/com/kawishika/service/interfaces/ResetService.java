package com.kawishika.service.interfaces;

import com.kawishika.dto.UserDTO;
import com.kawishika.service.SuperService;
import com.kawishika.util.CustomException;

public interface ResetService extends SuperService {
    boolean sendCode(String userName) throws CustomException;

    boolean checkCode(Integer code);

    boolean resetPassword(UserDTO userDTO) throws CustomException;

}
