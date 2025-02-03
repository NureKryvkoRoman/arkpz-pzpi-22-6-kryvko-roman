package ua.nure.kryvko.roman.Atark.greenhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/greenhouses")
public class GreenhouseController {

    private final GreenhouseService greenhouseService;

    @Autowired
    public GreenhouseController(GreenhouseService greenhouseService) {
        this.greenhouseService = greenhouseService;
    }

    /**
     * Add a new Greenhouse to the system
     * @param greenhouse
     * @return
     */
    @PostMapping
    public ResponseEntity<Greenhouse> createGreenhouse(@RequestBody Greenhouse greenhouse) {
        Greenhouse savedGreenhouse = greenhouseService.saveGreenhouse(greenhouse);
        return ResponseEntity.ok(savedGreenhouse);
    }

    /**
     * Get a Greenhouse by ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Greenhouse> getGreenhouseById(@PathVariable Integer id) {
        Optional<Greenhouse> greenhouse = greenhouseService.getGreenhouseById(id);
        return greenhouse.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Edit data about a Greenhouse
     * @param id
     * @param greenhouse
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<Greenhouse> updateGreenhouse(@PathVariable Integer id, @RequestBody Greenhouse greenhouse) {
        greenhouse.setId(id);
        Greenhouse updatedGreenhouse = greenhouseService.updateGreenhouse(greenhouse);
        return ResponseEntity.ok(updatedGreenhouse);
    }

    /**
     * Remove a Greenhouse from the system
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGreenhouse(@PathVariable Integer id) {
        greenhouseService.deleteGreenhouseById(id);
        return ResponseEntity.noContent().build();
    }
}
