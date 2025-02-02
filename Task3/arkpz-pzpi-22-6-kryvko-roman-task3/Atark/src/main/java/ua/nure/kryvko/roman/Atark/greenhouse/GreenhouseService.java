package ua.nure.kryvko.roman.Atark.greenhouse;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.nure.kryvko.roman.Atark.user.User;
import ua.nure.kryvko.roman.Atark.user.UserRepository;

import java.util.Optional;

@Service
public class GreenhouseService {

    private final GreenhouseRepository greenhouseRepository;
    private final UserRepository userRepository;

    @Autowired
    public GreenhouseService(GreenhouseRepository greenhouseRepository, UserRepository userRepository) {
        this.greenhouseRepository = greenhouseRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Greenhouse saveGreenhouse(Greenhouse greenhouse) {
        User owner = userRepository.findById(greenhouse.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Ensure the user is a managed entity
        greenhouse.setUser(owner);
        return greenhouseRepository.save(greenhouse);
    }

    public Optional<Greenhouse> getGreenhouseById(Integer id) {
        return greenhouseRepository.findById(id);
    }

    @Transactional
    public Greenhouse updateGreenhouse(Integer id, Greenhouse greenhouse) {
        greenhouse.setId(id);
        if (greenhouse.getId() == null || !greenhouseRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Greenhouse ID not found for update.");
        }

        User owner = userRepository.findById(greenhouse.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        greenhouse.setUser(owner);
        return greenhouseRepository.save(greenhouse);
    }

    @Transactional
    public void deleteGreenhouseById(Integer id) {
        if (!greenhouseRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Greenhouse ID not found for deletion.");
        }
        greenhouseRepository.deleteById(id);
    }
}
