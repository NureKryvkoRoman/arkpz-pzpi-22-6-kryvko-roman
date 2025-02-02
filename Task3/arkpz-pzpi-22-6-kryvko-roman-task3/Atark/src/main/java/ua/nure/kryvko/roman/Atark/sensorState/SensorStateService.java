package ua.nure.kryvko.roman.Atark.sensorState;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.nure.kryvko.roman.Atark.sensor.Sensor;
import ua.nure.kryvko.roman.Atark.sensor.SensorRepository;

import java.util.Optional;

@Service
public class SensorStateService {

    private final SensorStateRepository sensorStateRepository;
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorStateService(SensorStateRepository sensorStateRepository, SensorRepository sensorRepository) {
        this.sensorStateRepository = sensorStateRepository;
        this.sensorRepository = sensorRepository;
    }

    @Transactional
    public SensorState saveSensorState(SensorState sensorState) {
        Sensor owner = sensorRepository.findById(sensorState.getSensor().getId())
                .orElseThrow(()-> new IllegalArgumentException("Sensor not found"));
        sensorState.setSensor(owner);
        return sensorStateRepository.save(sensorState);
    }

    public Optional<SensorState> getSensorStateById(Integer id) {
        return sensorStateRepository.findById(id);
    }

    @Transactional
    public SensorState updateSensorState(SensorState sensorState) {
        if (sensorState.getId() == null || !sensorStateRepository.existsById(sensorState.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SensorState ID not found for update.");
        }
        return sensorStateRepository.save(sensorState);
    }

    @Transactional
    public void deleteSensorStateById(Integer id) {
        if (!sensorStateRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "SensorState ID not found for update.");
        }

        sensorStateRepository.deleteById(id);
    }
}
