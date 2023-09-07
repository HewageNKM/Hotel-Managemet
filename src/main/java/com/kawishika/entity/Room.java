package com.kawishika.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.engine.internal.Cascade;

import java.util.List;

@Entity
@Setter
@Getter
@NoArgsConstructor
@ToString
public class Room {
    @Id
    private String Room_Number;
    private String Status;
    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE,CascadeType.DETACH,CascadeType.REFRESH})
    private RoomCategory roomCategory;
    @OneToMany(mappedBy = "room")
    private List<Reserve> reserves;
    public Room(String roomNumber, String status) {
        Room_Number = roomNumber;
        Status = status;
    }
}
