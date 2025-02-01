package ua.nure.kryvko.roman.Atark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.kryvko.roman.Atark.greenhouse.GreenhouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ControllerService {

    private final ControllerRepository controllerRepository;
    private final GreenhouseRepository greenhouseRepository;

    @Autowired
    public ControllerService(ControllerRepository controllerRepository, GreenhouseRepository greenhouseRepository) {
        this.controllerRepository = controllerRepository;
        this.greenhouseRepository = greenhouseRepository;
    }

    public Controller createController(Controller controller) {
        if (controller.getGreenhouse() == null || !greenhouseRepository.existsById(controller.getGreenhouse().getId())) {
            throw new IllegalArgumentException("Greenhouse must exist to create a controller.");
        }
        return controllerRepository.save(controller);
    }

    public Optional<Controller> getControllerById(Integer id) {
        return controllerRepository.findById(id);
    }

    public List<Controller> getAllControllers() {
        return controllerRepository.findAll();
    }

    public void deleteController(Integer id) {
        controllerRepository.deleteById(id);
    }
}