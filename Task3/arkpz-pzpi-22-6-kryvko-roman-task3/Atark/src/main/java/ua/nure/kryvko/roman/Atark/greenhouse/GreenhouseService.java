package ua.nure.kryvko.roman.Atark.greenhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public Greenhouse updateGreenhouse(Greenhouse greenhouse) {
        if (greenhouse.getId() == null || !greenhouseRepository.existsById(greenhouse.getId())) {
            throw new IllegalArgumentException("Greenhouse ID not found for update.");
        }

        User owner = userRepository.findById(greenhouse.getUser().getId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        greenhouse.setUser(owner);
        return greenhouseRepository.save(greenhouse);
    }

    public void deleteGreenhouseById(Integer id) {
        greenhouseRepository.deleteById(id);
    }
}
