package com.elmenus.drones.model.entity;

import com.elmenus.drones.shared.constant.Model;
import com.elmenus.drones.shared.constant.State;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "drone")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Drone {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, unique = true, nullable = false)
    private String serialNumber;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Model model;

    @Column(nullable = false)
    private int weightLimit;

    @Column(nullable = false)
    private int batteryCapacity;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private State state;
}
