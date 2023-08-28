package com.kawishika.dto.tm;

import javafx.scene.control.Button;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.awt.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserTM {
    private String UserName;
    private String Password;
    private String Email;
    private String Status;
}
