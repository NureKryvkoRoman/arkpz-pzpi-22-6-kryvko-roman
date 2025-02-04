package ua.nure.kryvko.roman.Atark.sensorState;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.kryvko.roman.Atark.greenhouse.Greenhouse;
import ua.nure.kryvko.roman.Atark.sensor.Sensor;

import java.util.List;

public interface SensorStateRepository extends JpaRepository<SensorState, Integer> {
    List<SensorState> findBySensor (Sensor sensor);
}
