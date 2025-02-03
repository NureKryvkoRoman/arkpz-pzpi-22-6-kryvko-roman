package ua.nure.kryvko.roman.Atark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/controllers")
public class ControllerController {

    private final ControllerService controllerService;

    @Autowired
    public ControllerController(ControllerService controllerService) {
        this.controllerService = controllerService;
    }

    /**
     * Add a new Controller to the system
     * @param controller
     * @return
     */
    @PostMapping
    public ResponseEntity<Controller> createController(@RequestBody Controller controller) {
        try {
            Controller savedController = controllerService.createController(controller);
            return new ResponseEntity<>(savedController, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Get a Controller by ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<Controller> getControllerById(@PathVariable Integer id) {
        return controllerService.getControllerById(id)
                .map(controller -> new ResponseEntity<>(controller, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Get all Controllers
     * @return
     */
    @GetMapping
    public List<Controller> getAllControllers() {
        return controllerService.getAllControllers();
    }

    /**
     * Delete a Controller by ID
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteController(@PathVariable Integer id) {
        controllerService.deleteController(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}