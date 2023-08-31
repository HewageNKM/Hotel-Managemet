package com.kawishika.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

import java.time.LocalDate;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class StudentTM {
    private String id;
    private String name;
    private LocalDate birthday;
    private String gender;
    private String email;
    private String phoneNumber;
    private String status;
    private Button edit;
    private Button delete;
}
