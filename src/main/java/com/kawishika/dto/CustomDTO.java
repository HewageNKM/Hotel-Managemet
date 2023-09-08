package com.kawishika.dto;

import javafx.scene.control.Button;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;

@Getter
@Setter
@ToString
public class CustomDTO {
    private String roomNumber;
    private String status;
    private String roomId;
    private String type;
    private Double cost;

    private String reserveId;
    private String studentId;
    private double total;
    private String paymentStatus;
    private Date checkInDate;
    private Date checkOutDate;

    public CustomDTO(String roomNumber, String status, String roomId, String type) {
        this.roomNumber = roomNumber;
        this.status = status;
        this.roomId = roomId;
        this.type = type;
    }
    public CustomDTO(String reserveId, String studentId,String paymentStatus) {
        this.reserveId = reserveId;
        this.studentId = studentId;
        this.paymentStatus = paymentStatus;
    }
   public CustomDTO(String reserveId, String studentId, String roomNumber, Double total,String paymentStatus, Date checkInDate, Date checkOutDate) {
        this.reserveId = reserveId;
        this.studentId = studentId;
        this.roomNumber = roomNumber;
        this.total = total;
        this.paymentStatus = paymentStatus;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    public CustomDTO(String reserveId, String studentId, String roomNumber, Double total,String paymentStatus) {
        this.reserveId = reserveId;
        this.studentId = studentId;
        this.roomNumber = roomNumber;
        this.total = total;
        this.paymentStatus = paymentStatus;
    }

    public CustomDTO(String roomNumber, String roomId, Double cost) {
        this.roomNumber = roomNumber;
        this.roomId = roomId;
        this.cost = cost;
    }

    public CustomDTO() {
    }
}
