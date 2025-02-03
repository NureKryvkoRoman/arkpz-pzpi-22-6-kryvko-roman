package ua.nure.kryvko.roman.Atark.sensor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/sensors")
public class SensorController {

    private final SensorService sensorService;

    @Autowired
    public SensorController(SensorService sensorService) {
        this.sensorService = sensorService;
    }

    /**
     * Add a new sensor to the system
     * @param sensor
     * @return
     */
    @PostMapping
    public ResponseEntity<Sensor> createSensor(@RequestBody Sensor sensor) {
        Sensor savedSensor = sensorService.saveSensor(sensor);
        return ResponseEntity.ok(savedSensor);
    }

    /**
     * Get Sensor by ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getSensorById(@PathVariable Integer id) {
        Optional<Sensor> sensor = sensorService.getSensorById(id);
        return sensor.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Edit sensor data by ID
     * @param id
     * @param sensor
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Sensor> updateSensor(@PathVariable Integer id, @RequestBody Sensor sensor) {
        sensor.setId(id);
        Sensor updatedSensor = sensorService.updateSensor(sensor);
        return ResponseEntity.ok(updatedSensor);
    }

    /**
     * Delete sensor by ID
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Integer id) {
        sensorService.deleteSensorById(id);
        return ResponseEntity.noContent().build();
    }
}
