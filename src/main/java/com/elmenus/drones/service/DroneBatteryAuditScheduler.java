package com.elmenus.drones.service;

import com.elmenus.drones.model.entity.Drone;
import com.elmenus.drones.model.entity.DroneBatteryAudit;
import com.elmenus.drones.repository.DroneBatteryAuditRepository;
import com.elmenus.drones.repository.DroneRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class DroneBatteryAuditScheduler {

    @Autowired
    private DroneRepository droneRepository;

    @Autowired
    private DroneBatteryAuditRepository auditRepository;


    @Scheduled(fixedRate = 300000)
    public void logDroneBatteryLevels() {
        log.info("log drones battery levels");

        List<Drone> drones = droneRepository.findAll();
        List<DroneBatteryAudit> droneBatteryAudits = drones.stream()
                .map(this::createBatteryAudit)
                .toList();

        auditRepository.saveAll(droneBatteryAudits);
    }

    private DroneBatteryAudit createBatteryAudit(Drone drone) {
        return DroneBatteryAudit.builder()
                .droneId(drone.getId())
                .droneSerialNumber(drone.getSerialNumber())
                .batteryLevel(drone.getBatteryCapacity())
                .timestamp(LocalDateTime.now())
                .build();
    }
}
