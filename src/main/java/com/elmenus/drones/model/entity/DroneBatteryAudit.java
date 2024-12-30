package com.elmenus.drones.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "drone_battery_audit_log")
@Data
@Builder
public class DroneBatteryAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "drone_id", nullable = false)
    private Long droneId;

    @Column(name = "drone_serial_number", nullable = false)
    private String droneSerialNumber;

    @Column(nullable = false)
    private int batteryLevel;

    @Column(nullable = false)
    private LocalDateTime timestamp;
}
