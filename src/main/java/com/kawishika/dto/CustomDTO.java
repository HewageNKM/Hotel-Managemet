package com.kawishika.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CustomDTO {
    private String roomNumber;
    private String status;
    private String roomId;
    private String type;
    public CustomDTO(String roomNumber, String status, String roomId, String type) {
        this.roomNumber = roomNumber;
        this.status = status;
        this.roomId = roomId;
        this.type = type;
    }
}
