package com.elmenus.drones.service;

import com.elmenus.drones.model.dto.DroneDTO;
import com.elmenus.drones.model.dto.MedicationDTO;

import java.util.List;

public interface DroneService {
    /**
     * Registers a new drone.
     * @param droneDTO The drone details.
     * @return The registered drone.
     */
    DroneDTO registerDrone(DroneDTO droneDTO);

    /**
     * Loads a drone with medication items.
     * @param droneId The ID of the drone.
     * @param medications List of medications to load.
     */
    void loadDrone(Long droneId, List<MedicationDTO> medications);

    /**
     * Retrieves the medications loaded on a given drone.
     * @param droneId The ID of the drone.
     * @return List of medications loaded.
     */
    List<MedicationDTO> getLoadedMedications(Long droneId);

    /**
     * Retrieves all drones available for loading.
     * @return List of available drones.
     */
    List<DroneDTO> getAvailableDrones();

    /**
     * Retrieves the battery level of a given drone.
     * @param droneId The ID of the drone.
     * @return The battery level as a percentage.
     */
    int getDroneBatteryLevel(Long droneId);
}
