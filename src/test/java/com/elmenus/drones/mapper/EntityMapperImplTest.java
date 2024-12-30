package com.elmenus.drones.mapper;

import com.elmenus.drones.model.dto.DroneDTO;
import com.elmenus.drones.model.dto.MedicationDTO;
import com.elmenus.drones.model.entity.Drone;
import com.elmenus.drones.model.entity.Medication;
import com.elmenus.drones.shared.mapper.EntityMapperImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.*;

class EntityMapperImplTest {

    private EntityMapperImpl<Drone, DroneDTO> droneMapper;
    private EntityMapperImpl<Medication, MedicationDTO> medicationMapper;

    @BeforeEach
    void setUp() {
        ModelMapper modelMapper = new ModelMapper();
        droneMapper = new EntityMapperImpl<>(modelMapper);
        medicationMapper = new EntityMapperImpl<>(modelMapper);
    }

    @Test
    void testDroneToEntity() {
        DroneDTO droneDTO = new DroneDTO();
        droneDTO.setId(1L);
        droneDTO.setBatteryCapacity(80);

        Drone drone = droneMapper.toEntity(droneDTO, Drone.class);

        assertNotNull(drone);
        assertEquals(droneDTO.getId(), drone.getId());
        assertEquals(droneDTO.getBatteryCapacity(), drone.getBatteryCapacity());
    }

    @Test
    void testDroneToDTO() {
        Drone drone = new Drone();
        drone.setId(1L);
        drone.setBatteryCapacity(80);

        DroneDTO droneDTO = droneMapper.toDTO(drone, DroneDTO.class);

        assertNotNull(droneDTO);
        assertEquals(drone.getId(), droneDTO.getId());
        assertEquals(drone.getBatteryCapacity(), droneDTO.getBatteryCapacity());
    }

    @Test
    void testMedicationToEntity() {
        MedicationDTO medicationDTO = new MedicationDTO();
        medicationDTO.setName("Painkiller");
        medicationDTO.setWeight(50);

        Medication medication = medicationMapper.toEntity(medicationDTO, Medication.class);

        assertNotNull(medication);
        assertEquals(medicationDTO.getName(), medication.getName());
        assertEquals(medicationDTO.getWeight(), medication.getWeight());
    }

    @Test
    void testMedicationToDTO() {
        Medication medication = new Medication();
        medication.setName("Painkiller");
        medication.setWeight(50);

        MedicationDTO medicationDTO = medicationMapper.toDTO(medication, MedicationDTO.class);

        assertNotNull(medicationDTO);
        assertEquals(medication.getName(), medicationDTO.getName());
        assertEquals(medication.getWeight(), medicationDTO.getWeight());
    }
}