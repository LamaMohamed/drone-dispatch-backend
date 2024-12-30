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
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DroneServiceImpl implements DroneService {

    @Autowired
    private DroneRepository droneRepository;
    @Autowired
    private MedicationRepository medicationRepository;
    @Autowired
    private EntityMapper<Drone, DroneDTO> droneMapper;
    @Autowired
    private EntityMapper<Medication, MedicationDTO> medicationMapper;
    @Autowired
    private DroneValidator droneValidator;

    @Override
    public DroneDTO registerDrone(DroneDTO droneDTO) {
        Drone drone = droneMapper.toEntity(droneDTO, Drone.class);
        Drone savedDrone = droneRepository.save(drone);
        return droneMapper.toDTO(savedDrone, DroneDTO.class);
    }

    @Override
    public void loadDrone(Long droneId, List<MedicationDTO> medications) {
        Drone drone = droneRepository.findById(droneId)
                .orElseThrow(() -> new ResourceNotFoundException("Drone", droneId.toString()));

        droneValidator.validateDroneForLoading(drone, medications);

        List<Medication> medicationEntities = mapAndAssignMedicationsToDrone(medications, drone);


        medicationRepository.saveAll(medicationEntities);
        drone.setState(State.LOADING);
        droneRepository.save(drone);
    }

    @Override
    public List<MedicationDTO> getLoadedMedications(Long droneId) {
        return medicationRepository.findByDroneId(droneId)
                .stream().map(medication -> medicationMapper.toDTO(medication, MedicationDTO.class)).toList();
    }

    @Override
    public List<DroneDTO> getAvailableDrones() {
        return droneRepository.findByStateInAndBatteryCapacityGreaterThan(
                        List.of(State.IDLE, State.RETURNING), 25)
                .stream()
                .map(drone -> droneMapper.toDTO(drone, DroneDTO.class))
                .toList();
    }

    @Override
    public int getDroneBatteryLevel(Long droneId) {
        return droneRepository.findById(droneId)
                .orElseThrow(() -> new ResourceNotFoundException("Drone", droneId.toString()))
                .getBatteryCapacity();
    }

    private List<Medication> mapAndAssignMedicationsToDrone(List<MedicationDTO> medications, Drone drone) {
        return medications.stream()
                .map(medicationDTO -> {
                    Medication medication = medicationMapper.toEntity(medicationDTO, Medication.class);
                    medication.setDrone(drone);
                    return medication;
                })
                .collect(Collectors.toList());


    }
}
