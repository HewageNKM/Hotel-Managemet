package com.kawishika.dto;

import com.kawishika.entity.Reserve;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.sql.Date;
@Getter
@Setter
@ToString
public class ReserveDTO extends Reserve {
    public ReserveDTO() {
    }
    public ReserveDTO(String reserve_ID, Date reserve_Date, Date checkOut_Date, Double total, String payment_Status, String status) {
        super(reserve_ID, reserve_Date, checkOut_Date, total, payment_Status, status);
    }
}
