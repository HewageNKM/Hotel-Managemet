package com.kawishika.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.sql.Date;
import java.sql.Time;

import static org.hibernate.annotations.CascadeType.*;
import static org.hibernate.annotations.CascadeType.DETACH;
import static org.hibernate.annotations.CascadeType.PERSIST;

@Entity
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Reserve {
    @Id
    private String Reserve_ID;
    private Date Reserve_Date;
    private Date CheckOut_Date;
    private Double Total;
    private String Payment_Status;
    private String Status;
    @ManyToOne(cascade = {jakarta.persistence.CascadeType.REFRESH, jakarta.persistence.CascadeType.DETACH, jakarta.persistence.CascadeType.PERSIST, jakarta.persistence.CascadeType.MERGE})
    private Student student;
    @ManyToOne(cascade = {jakarta.persistence.CascadeType.REFRESH, jakarta.persistence.CascadeType.DETACH, jakarta.persistence.CascadeType.PERSIST, jakarta.persistence.CascadeType.MERGE})
    private Room room;

    public Reserve(String reserveId, Date reserveDate, Date checkOutDate, Double total, String paymentStatus, String status) {
        this.Reserve_ID = reserveId;
        this.Reserve_Date = reserveDate;
        this.CheckOut_Date = checkOutDate;
        this.Total = total;
        this.Payment_Status = paymentStatus;
        this.Status = status;
    }
}
