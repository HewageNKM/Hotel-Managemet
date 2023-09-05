package com.kawishika.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.sql.Date;
import java.util.List;

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
    private String Gender;
    private String Status;
    @OneToMany(mappedBy = "student")
    private List<Reserve> reserves;
    public Student(String student_ID, String student_Name, String student_Email, String phone_No, Date birthDay, String gender, String status) {
        Student_ID = student_ID;
        Student_Name = student_Name;
        Student_Email = student_Email;
        Phone_No = phone_No;
        BirthDay = birthDay;
        Gender = gender;
        Status = status;
    }
}
