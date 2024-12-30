package com.elmenus.drones.controller;


import com.elmenus.drones.model.dto.DroneDTO;
import com.elmenus.drones.model.dto.MedicationDTO;
import com.elmenus.drones.service.DroneService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.drones}")
@Slf4j
public class DroneController {

    @Autowired
    private DroneService droneService;


    /**
     * Register a new drone
     * @param droneDTO: the drone dto
     * @return the newly registered drone
     */
    @PostMapping
    public ResponseEntity<DroneDTO> registerDrone(@Valid @RequestBody DroneDTO droneDTO) {
        log.info("Performing register new drone: {}", droneDTO);
        DroneDTO savedDrone = droneService.registerDrone(droneDTO);
        return ResponseEntity.ok(savedDrone);
    }


    /**
     * Load a drone with medication items
     * @param id: drone id
     * @param medications: list of the medications
     */
    @PostMapping("/{id}/load")
    public ResponseEntity<String> loadDroneWithMedication(
            @PathVariable Long id,
            @Valid @RequestBody List<MedicationDTO> medications) {
        log.info("Performing load drone <{}> with medications",id);
        droneService.loadDrone(id, medications);
        return ResponseEntity.ok("Drone loaded with medication successfully.");
    }


    /**
     * Check loaded medication items for a given drone
     * @param id: Drone Id
     * @return  A list of loaded medications for given drone
     */
    @GetMapping("/{id}/medications")
    public ResponseEntity<List<MedicationDTO>> getLoadedMedications(@PathVariable Long id) {
        log.info("Performing get loaded medications for drone: {}", id);
        List<MedicationDTO> medications = droneService.getLoadedMedications(id);
        return ResponseEntity.ok(medications);
    }


    /**
     * Check available drones for loading
     * @return A list of available drones
     */
    @GetMapping("/available")
    public ResponseEntity<List<DroneDTO>> getAvailableDrones() {
        log.info("Performing get available drones");
        List<DroneDTO> availableDrones = droneService.getAvailableDrones();
        return ResponseEntity.ok(availableDrones);
    }

    /**
     * Check drone battery level for a given drone
     * @param id: Drone id
     * @return Drone battery level
     */
    @GetMapping("/{id}/battery")
    public ResponseEntity<Integer> getDroneBatteryLevel(@PathVariable Long id) {
        log.info("Performing get battery level for drone: {}",id);
        int batteryLevel = droneService.getDroneBatteryLevel(id);
        return ResponseEntity.ok(batteryLevel);
    }
}
