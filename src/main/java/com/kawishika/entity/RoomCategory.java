package com.kawishika.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomCategory {
    @Id
    private String Room_ID;
    private String Room_Type;
    private Double Cost_Per_Week;
    @OneToMany(mappedBy = "roomCategory", cascade = CascadeType.ALL)
    private List<Room> rooms;
}
