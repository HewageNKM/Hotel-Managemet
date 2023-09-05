package com.kawishika.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Room {
    @Id
    private String Room_Number;
    private String Status;
    @ManyToOne (cascade = CascadeType.ALL)
    private RoomCategory roomCategory;
    @OneToMany(mappedBy = "room")
    private List<Reserve> reserves;
}
