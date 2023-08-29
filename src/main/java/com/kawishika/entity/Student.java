package com.kawishika.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.sql.Date;
import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Student {
    @Id
    private String Student_ID;
    private String Student_Name;
    private String Student_Email;
    private String Phone_No;
    private Date BirthDay;
    private String Status;
}
