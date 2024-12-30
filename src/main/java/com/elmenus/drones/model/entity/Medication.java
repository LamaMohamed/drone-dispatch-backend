package com.elmenus.drones.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity
@Table(name = "medication")
@Data
public class Medication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Pattern(regexp = "^[A-Za-z0-9-_]+$")
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int weight;

    @Pattern(regexp = "^[A-Z0-9_]+$")
    @Column(nullable = false)
    private String code;

    @Lob
    @Column(nullable = false)
    private byte[] image;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drone_id", nullable = false)
    private Drone drone;
}
