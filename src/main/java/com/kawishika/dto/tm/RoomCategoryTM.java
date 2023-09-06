package com.kawishika.dto.tm;

import com.kawishika.entity.RoomCategory;
import javafx.scene.control.Button;
import lombok.*;


@Getter
@Setter
@ToString
public class RoomCategoryTM extends RoomCategory {
    private Button edit;
    private Button delete;
    public RoomCategoryTM(String roomId, String roomType, Double costPerWeek, Button edit, Button delete) {
        super(roomId, roomType, costPerWeek);
        this.edit = edit;
        this.delete = delete;
    }
}
