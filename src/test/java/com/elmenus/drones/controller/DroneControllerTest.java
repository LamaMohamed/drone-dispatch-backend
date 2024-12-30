package com.elmenus.drones.controller;

import com.elmenus.drones.model.dto.DroneDTO;
import com.elmenus.drones.model.dto.MedicationDTO;
import com.elmenus.drones.service.DroneService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class DroneControllerTest {

    @Mock
    private DroneService droneService;

    @InjectMocks
    private DroneController droneController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerDrone() {
        DroneDTO droneDTO = new DroneDTO();
        droneDTO.setId(1L);
        when(droneService.registerDrone(droneDTO)).thenReturn(droneDTO);

        ResponseEntity<DroneDTO> response = droneController.registerDrone(droneDTO);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(droneDTO, response.getBody());
        verify(droneService, times(1)).registerDrone(droneDTO);
    }

    @Test
    void loadDroneWithMedication() {
        Long droneId = 1L;
        List<MedicationDTO> medications = Collections.singletonList(new MedicationDTO());

        doNothing().when(droneService).loadDrone(droneId, medications);

        ResponseEntity<String> response = droneController.loadDroneWithMedication(droneId, medications);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Drone loaded with medication successfully.", response.getBody());
        verify(droneService, times(1)).loadDrone(droneId, medications);
    }

    @Test
    void getLoadedMedications() {
        Long droneId = 1L;
        List<MedicationDTO> medications = Arrays.asList(new MedicationDTO(), new MedicationDTO());
        when(droneService.getLoadedMedications(droneId)).thenReturn(medications);

        ResponseEntity<List<MedicationDTO>> response = droneController.getLoadedMedications(droneId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(medications, response.getBody());
        verify(droneService, times(1)).getLoadedMedications(droneId);
    }

    @Test
    void getAvailableDrones() {
        List<DroneDTO> availableDrones = Arrays.asList(new DroneDTO(), new DroneDTO());
        when(droneService.getAvailableDrones()).thenReturn(availableDrones);

        ResponseEntity<List<DroneDTO>> response = droneController.getAvailableDrones();

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(availableDrones, response.getBody());
        verify(droneService, times(1)).getAvailableDrones();
    }

    @Test
    void getDroneBatteryLevel() {
        Long droneId = 1L;
        int batteryLevel = 75;
        when(droneService.getDroneBatteryLevel(droneId)).thenReturn(batteryLevel);

        ResponseEntity<Integer> response = droneController.getDroneBatteryLevel(droneId);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals(batteryLevel, response.getBody());
        verify(droneService, times(1)).getDroneBatteryLevel(droneId);
    }
}

