package com.elmenus.drones.service;

import com.elmenus.drones.model.dto.DroneDTO;
import com.elmenus.drones.model.dto.MedicationDTO;
import com.elmenus.drones.model.entity.Drone;
import com.elmenus.drones.model.entity.Medication;
import com.elmenus.drones.repository.DroneRepository;
import com.elmenus.drones.repository.MedicationRepository;
import com.elmenus.drones.shared.constant.State;
import com.elmenus.drones.shared.exception.custom.ResourceNotFoundException;
import com.elmenus.drones.shared.mapper.EntityMapper;
import com.elmenus.drones.validation.DroneValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DroneServiceImplTest {

    @Mock
    private DroneRepository droneRepository;

    @Mock
    private MedicationRepository medicationRepository;

    @Mock
    private EntityMapper<Drone, DroneDTO> droneMapper;

    @Mock
    private EntityMapper<Medication, MedicationDTO> medicationMapper;

    @Mock
    private DroneValidator droneValidator;

    @InjectMocks
    private DroneServiceImpl droneService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerDrone() {
        DroneDTO droneDTO = new DroneDTO();
        Drone drone = new Drone();
        Drone savedDrone = new Drone();

        when(droneMapper.toEntity(droneDTO, Drone.class)).thenReturn(drone);
        when(droneRepository.save(drone)).thenReturn(savedDrone);
        when(droneMapper.toDTO(savedDrone, DroneDTO.class)).thenReturn(droneDTO);

        DroneDTO result = droneService.registerDrone(droneDTO);

        assertEquals(droneDTO, result);
        verify(droneMapper, times(1)).toEntity(droneDTO, Drone.class);
        verify(droneRepository, times(1)).save(drone);
        verify(droneMapper, times(1)).toDTO(savedDrone, DroneDTO.class);
    }

    @Test
    void loadDrone() {
        Long droneId = 1L;
        Drone drone = new Drone();
        drone.setState(State.IDLE);

        List<MedicationDTO> medicationDTOs = Arrays.asList(new MedicationDTO(), new MedicationDTO());
        List<Medication> medications = Arrays.asList(new Medication(), new Medication());

        when(droneRepository.findById(droneId)).thenReturn(Optional.of(drone));
        doNothing().when(droneValidator).validateDroneForLoading(drone, medicationDTOs);
        when(medicationMapper.toEntity(any(MedicationDTO.class), eq(Medication.class)))
                .thenAnswer(invocation -> new Medication());

        ArgumentCaptor<List<Medication>> captor = ArgumentCaptor.forClass(List.class);

        droneService.loadDrone(droneId, medicationDTOs);

        verify(droneValidator, times(1)).validateDroneForLoading(drone, medicationDTOs);
        verify(medicationRepository, times(1)).saveAll(captor.capture());
        verify(droneRepository, times(1)).save(drone);

        assertEquals(2, captor.getValue().size());
        assertEquals(State.LOADING, drone.getState());
    }

    @Test
    void getLoadedMedications() {
        Long droneId = 1L;
        Medication medication = new Medication();
        MedicationDTO medicationDTO = new MedicationDTO();

        when(medicationRepository.findByDroneId(droneId)).thenReturn(Arrays.asList(medication));
        when(medicationMapper.toDTO(medication, MedicationDTO.class)).thenReturn(medicationDTO);

        List<MedicationDTO> result = droneService.getLoadedMedications(droneId);

        assertEquals(1, result.size());
        assertEquals(medicationDTO, result.get(0));
        verify(medicationRepository, times(1)).findByDroneId(droneId);
        verify(medicationMapper, times(1)).toDTO(medication, MedicationDTO.class);
    }

    @Test
    void getAvailableDrones() {
        Drone drone = new Drone();
        DroneDTO droneDTO = new DroneDTO();

        when(droneRepository.findByStateInAndBatteryCapacityGreaterThan(
                List.of(State.IDLE, State.RETURNING), 25)).thenReturn(List.of(drone));
        when(droneMapper.toDTO(drone, DroneDTO.class)).thenReturn(droneDTO);

        List<DroneDTO> result = droneService.getAvailableDrones();

        assertEquals(1, result.size());
        assertEquals(droneDTO, result.get(0));
        verify(droneRepository, times(1)).findByStateInAndBatteryCapacityGreaterThan(
                List.of(State.IDLE, State.RETURNING), 25);
        verify(droneMapper, times(1)).toDTO(drone, DroneDTO.class);
    }

    @Test
    void getDroneBatteryLevel() {
        Long droneId = 1L;
        Drone drone = new Drone();
        drone.setBatteryCapacity(75);

        when(droneRepository.findById(droneId)).thenReturn(Optional.of(drone));

        int result = droneService.getDroneBatteryLevel(droneId);

        assertEquals(75, result);
        verify(droneRepository, times(1)).findById(droneId);
    }

    @Test
    void getDroneBatteryLevel_ResourceNotFound() {
        Long droneId = 1L;

        when(droneRepository.findById(droneId)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> droneService.getDroneBatteryLevel(droneId));
        verify(droneRepository, times(1)).findById(droneId);
    }
}
