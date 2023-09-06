package com.kawishika.dto.tm;

import javafx.scene.control.Button;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RoomTM {
    private String roomId;
    private String roomNumber;
    private String roomType;
    private String status;
    private Button edit;
    private Button delete;
}
