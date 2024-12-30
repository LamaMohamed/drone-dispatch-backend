package com.elmenus.drones.validation;

import com.elmenus.drones.model.dto.MedicationDTO;
import com.elmenus.drones.model.entity.Drone;
import com.elmenus.drones.shared.exception.custom.ValidationException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DroneValidator {
    public void validateDroneForLoading(Drone drone, List<MedicationDTO> medications) {
        validateBatteryCapacity(drone);
        validateWeightLimit(drone, medications);
    }

    private void validateBatteryCapacity(Drone drone) {
        if (drone.getBatteryCapacity() < 25) {
            throw new ValidationException("Drone battery level is below 25%");
        }
    }

    private void validateWeightLimit(Drone drone, List<MedicationDTO> medications) {
        int totalWeight = medications.stream()
                .mapToInt(MedicationDTO::getWeight)
                .sum();
        if (totalWeight > drone.getWeightLimit()) {
            throw new ValidationException("Total weight exceeds drone's weight limit");
        }
    }
}
