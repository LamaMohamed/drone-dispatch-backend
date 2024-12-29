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
@RequestMapping("${api.drone}")
@Slf4j
public class DroneController {

    @Autowired
    private DroneService droneService;


    /**
     * Register a new drone
     * @param droneDTO
     * @return
     */
    @PostMapping
    public ResponseEntity<DroneDTO> registerDrone(@Valid @RequestBody DroneDTO droneDTO) {
        DroneDTO savedDrone = droneService.registerDrone(droneDTO);
        return ResponseEntity.ok(savedDrone);
    }


    /**
     * Load a drone with medication items
     * @param id
     * @param medications
     * @return
     */
    @PostMapping("/{id}/load")
    public ResponseEntity<String> loadDroneWithMedication(
            @PathVariable Long id,
            @Valid @RequestBody List<MedicationDTO> medications) {
        droneService.loadDrone(id, medications);
        return ResponseEntity.ok("Drone loaded with medication successfully.");
    }


    /**
     * Check loaded medication items for a given drone
     * @param id
     * @return
     */
    @GetMapping("/{id}/medications")
    public ResponseEntity<List<MedicationDTO>> getLoadedMedications(@PathVariable Long id) {
        List<MedicationDTO> medications = droneService.getLoadedMedications(id);
        return ResponseEntity.ok(medications);
    }


    /**
     * Check available drones for loading
     * @return
     */
    @GetMapping("/available")
    public ResponseEntity<List<DroneDTO>> getAvailableDrones() {
        List<DroneDTO> availableDrones = droneService.getAvailableDrones();
        return ResponseEntity.ok(availableDrones);
    }

    /**
     * Check drone battery level for a given drone
     * @param id
     * @return
     */
    @GetMapping("/{id}/battery")
    public ResponseEntity<Integer> getDroneBatteryLevel(@PathVariable Long id) {
        int batteryLevel = droneService.getDroneBatteryLevel(id);
        return ResponseEntity.ok(batteryLevel);
    }
}
