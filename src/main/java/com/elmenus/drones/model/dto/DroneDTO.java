package com.elmenus.drones.model.dto;

import com.elmenus.drones.shared.constant.Model;
import com.elmenus.drones.shared.constant.State;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DroneDTO {
    @NotBlank(message = "Serial number is mandatory")
    @Size(max = 100, message = "Serial number must not exceed 100 characters")
    private String serialNumber;

    @NotNull(message = "Model is mandatory")
    private Model model;

    @Min(value = 1, message = "Weight limit must be greater than 0")
    @Max(value = 500, message = "Weight limit must not exceed 500 grams")
    private Integer weightLimit;

    @NotNull(message = "Battery capacity is mandatory")
    @Min(value = 0, message = "Battery capacity must be at least 0%")
    @Max(value = 100, message = "Battery capacity must not exceed 100%")
    private Integer batteryCapacity;

    @NotNull(message = "State is mandatory")
    private State state;
}
