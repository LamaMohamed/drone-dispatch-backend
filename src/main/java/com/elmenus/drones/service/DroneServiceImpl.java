package com.elmenus.drones.service;

import com.elmenus.drones.model.dto.DroneDTO;
import com.elmenus.drones.model.dto.MedicationDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DroneServiceImpl implements DroneService{
    @Override
    public DroneDTO registerDrone(DroneDTO droneDTO) {
        return null;
    }

    @Override
    public void loadDrone(Long droneId, List<MedicationDTO> medications) {

    }

    @Override
    public List<MedicationDTO> getLoadedMedications(Long droneId) {
        return List.of();
    }

    @Override
    public List<DroneDTO> getAvailableDrones() {
        return List.of();
    }

    @Override
    public int getDroneBatteryLevel(Long droneId) {
        return 0;
    }
}
