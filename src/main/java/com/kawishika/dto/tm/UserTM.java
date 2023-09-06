package com.kawishika.dto.tm;

import com.kawishika.entity.User;
import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
@Getter
@Setter
public class UserTM extends User {
    public UserTM (String userName, String password, String email, String status) {
        super(userName, password, email, status);
    }
}
