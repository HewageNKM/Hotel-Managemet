package com.kawishika.dto;

import com.kawishika.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends User {
    public UserDTO(String userName, String password, String email, String status) {
        super(userName, password, email, status);
    }

    public UserDTO(String userName, String password) {
        super(userName, password);
    }
}
