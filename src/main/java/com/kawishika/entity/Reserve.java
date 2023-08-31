package com.kawishika.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.sql.Date;
import java.sql.Time;
@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserve {
    @Id
    private String Reserve_ID;
    private String Student_ID;
    private String Room_Type;
    private Double Cost_Per_Week;
    private Double Total;
    private String Payment_Status;
    private Date Date;
    private Time Time;
    private Date Reserve_Date;
    private Date CheckOut_Date;
    private String Status;
    @ManyToOne
    @Cascade(CascadeType.ALL)
    private Student student;
}
