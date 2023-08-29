package com.kawishika.dto;

import com.kawishika.entity.Student;

import java.sql.Date;
import java.time.LocalDate;

public class StudentDTO extends Student {
    public StudentDTO(String id, String name, String email, String phoneNumber, LocalDate birthday, String status) {
        super(id, name, email, phoneNumber, Date.valueOf(birthday), status);
    }
}
