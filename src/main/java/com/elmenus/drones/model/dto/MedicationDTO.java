package com.elmenus.drones.model.dto;

import com.elmenus.drones.model.entity.Drone;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MedicationDTO {

    private Long id;
    
    @NotNull(message = "Name is mandatory")
    @Pattern(regexp = "^[A-Za-z0-9-_]+$", message = "Invalid name format")
    private String name;

    @NotNull(message = "Weight is mandatory")
    private Integer weight;

    @NotNull(message = "Code is mandatory")
    @Pattern(regexp = "^[A-Z0-9_]+$", message = "Invalid code format")
    private String code;

    private byte[] image;
}
