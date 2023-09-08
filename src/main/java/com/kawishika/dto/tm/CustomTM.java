package com.kawishika.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

import java.sql.Date;

@Getter
@Setter
@ToString
public class CustomTM {
    private String reserveId;
    private String studentId;
    private String roomNumber;
    private Double total;
    private String paymentStatus;
    private Date checkInDate;
    private Date checkOutDate;
    private Button received;
    public CustomTM(String reserveId, String studentId, String roomNumber, Double total,String paymentStatus, Date checkInDate, Date checkOutDate) {
        this.reserveId = reserveId;
        this.studentId = studentId;
        this.roomNumber = roomNumber;
        this.total = total;
        this.paymentStatus = paymentStatus;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
    }
    public CustomTM(String reserveId, String studentId, String roomNumber, Double total,String paymentStatus,Button received){
        this.reserveId = reserveId;
        this.studentId = studentId;
        this.roomNumber = roomNumber;
        this.total = total;
        this.paymentStatus = paymentStatus;
        if(paymentStatus.equals("Paid")){
            this.received = received;
            received.setDisable(true);
        }else {
            this.received = received;
            received.setDisable(false);
        }
    }
}
