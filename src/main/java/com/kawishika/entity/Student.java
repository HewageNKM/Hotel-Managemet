package com.kawishika.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.sql.Date;
import java.util.ArrayList;

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
    private ArrayList<Reserve> reserves;

    public Student(String id, String name, String email, String phone, Date date, String gender, String status) {
        this.Student_ID=id;
        this.Student_Name=name;
        this.Student_Email=email;
        this.Phone_No =phone;
        this.BirthDay=date;
        this.Gender =gender;
        this.Status=status;
    }
}
