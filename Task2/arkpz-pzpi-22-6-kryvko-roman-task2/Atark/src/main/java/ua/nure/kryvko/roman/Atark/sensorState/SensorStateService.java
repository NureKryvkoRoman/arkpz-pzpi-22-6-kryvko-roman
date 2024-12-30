package ua.nure.kryvko.roman.Atark.sensorState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SensorStateService {

    private final SensorStateRepository sensorStateRepository;

    @Autowired
    public SensorStateService(SensorStateRepository sensorStateRepository) {
        this.sensorStateRepository = sensorStateRepository;
    }

    public SensorState saveSensorState(SensorState sensorState) {
        return sensorStateRepository.save(sensorState);
    }

    public Optional<SensorState> getSensorStateById(Integer id) {
        return sensorStateRepository.findById(id);
    }

    public SensorState updateSensorState(SensorState sensorState) {
        if (sensorState.getId() == null || !sensorStateRepository.existsById(sensorState.getId())) {
            throw new IllegalArgumentException("SensorState ID not found for update.");
        }
        return sensorStateRepository.save(sensorState);
    }

    public void deleteSensorStateById(Integer id) {
        sensorStateRepository.deleteById(id);
    }
}
