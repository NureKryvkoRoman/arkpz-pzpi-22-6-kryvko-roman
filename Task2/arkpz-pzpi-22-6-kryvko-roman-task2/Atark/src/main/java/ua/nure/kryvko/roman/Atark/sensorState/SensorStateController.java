package ua.nure.kryvko.roman.Atark.sensorState;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/sensor-states")
public class SensorStateController {

    private final SensorStateService sensorStateService;

    @Autowired
    public SensorStateController(SensorStateService sensorStateService) {
        this.sensorStateService = sensorStateService;
    }

    @PostMapping
    public ResponseEntity<SensorState> createSensorState(@RequestBody SensorState sensorState) {
        SensorState savedSensorState = sensorStateService.saveSensorState(sensorState);
        return ResponseEntity.ok(savedSensorState);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorState> getSensorStateById(@PathVariable Integer id) {
        Optional<SensorState> sensorState = sensorStateService.getSensorStateById(id);
        return sensorState.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<SensorState> updateSensorState(@PathVariable Integer id, @RequestBody SensorState sensorState) {
        sensorState.setId(id);
        SensorState updatedSensorState = sensorStateService.updateSensorState(sensorState);
        return ResponseEntity.ok(updatedSensorState);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensorState(@PathVariable Integer id) {
        sensorStateService.deleteSensorStateById(id);
        return ResponseEntity.noContent().build();
    }
}
