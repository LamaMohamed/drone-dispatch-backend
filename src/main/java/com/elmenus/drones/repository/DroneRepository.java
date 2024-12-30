package com.elmenus.drones.repository;

import com.elmenus.drones.model.entity.Drone;
import com.elmenus.drones.shared.constant.State;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    List<Drone> findByStateInAndBatteryCapacityGreaterThan(List<State> states, int batteryCapacity);
}
