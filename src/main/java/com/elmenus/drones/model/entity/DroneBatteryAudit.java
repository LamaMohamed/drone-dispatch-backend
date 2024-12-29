package com.elmenus.drones.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "drone_battery_audit")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DroneBatteryAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String droneSerialNumber;

    @Column(nullable = false)
    private int batteryLevel;

    @Column(nullable = false)
    private LocalDateTime timestamp;
}
