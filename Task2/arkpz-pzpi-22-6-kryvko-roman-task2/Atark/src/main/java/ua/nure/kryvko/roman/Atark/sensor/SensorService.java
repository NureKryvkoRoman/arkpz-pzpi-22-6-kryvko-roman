package ua.nure.kryvko.roman.Atark.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensorService {

    private final SensorRepository sensorRepository;

    @Autowired
    public SensorService(SensorRepository sensorRepository) {
        this.sensorRepository = sensorRepository;
    }

    public Sensor saveSensor(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    public Optional<Sensor> getSensorById(Integer id) {
        return sensorRepository.findById(id);
    }

    public Sensor updateSensor(Sensor sensor) {
        if (sensor.getId() == null || !sensorRepository.existsById(sensor.getId())) {
            throw new IllegalArgumentException("Sensor ID not found for update.");
        }
        return sensorRepository.save(sensor);
    }

    public void deleteSensorById(Integer id) {
        sensorRepository.deleteById(id);
    }
}
