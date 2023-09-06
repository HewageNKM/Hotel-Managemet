package com.kawishika.dto.tm;

import com.kawishika.entity.Student;
import javafx.scene.control.Button;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;
@Getter
@Setter
@ToString
public class StudentTM extends Student {
    private Button edit;
    private Button delete;

    public StudentTM(String studentId, String studentName, LocalDate localDate, String gender, String studentEmail, String phoneNo, String status, Button edit, Button delete) {
        super(studentId,studentName,studentEmail,phoneNo, Date.valueOf(localDate),gender,status);
        this.edit = edit;
        this.delete = delete;
    }
}
