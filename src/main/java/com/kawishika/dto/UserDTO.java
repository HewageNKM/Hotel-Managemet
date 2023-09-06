package com.kawishika.dto;

import com.kawishika.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class UserDTO extends User {
    public UserDTO(String userName, String password, String email, String status) {
        super(userName, password, email, status);
    }
}
