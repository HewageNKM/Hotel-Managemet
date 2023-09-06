package com.kawishika.dto;

import com.kawishika.entity.Student;

import java.sql.Date;
import java.time.LocalDate;

public class StudentDTO extends Student {
    public StudentDTO(String id, String name, String email, String phone, LocalDate birthday, String gender, String status) {
        super(id,name,email,phone, Date.valueOf(birthday),gender,status);
    }
}
