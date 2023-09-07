package com.kawishika.dto.tm;

import com.kawishika.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserTM extends User {
    public UserTM(String userName, String password, String email, String status) {
        super(userName, password, email, status);
    }
}
