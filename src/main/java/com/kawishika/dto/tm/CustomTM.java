package com.kawishika.dto.tm;

import lombok.*;

import java.sql.Date;

@NoArgsConstructor
@AllArgsConstructor
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
}
