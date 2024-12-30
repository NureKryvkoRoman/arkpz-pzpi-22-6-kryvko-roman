package ua.nure.kryvko.roman.Atark.greenhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GreenhouseService {

    private final GreenhouseRepository greenhouseRepository;

    @Autowired
    public GreenhouseService(GreenhouseRepository greenhouseRepository) {
        this.greenhouseRepository = greenhouseRepository;
    }

    public Greenhouse saveGreenhouse(Greenhouse greenhouse) {
        return greenhouseRepository.save(greenhouse);
    }

    public Optional<Greenhouse> getGreenhouseById(Integer id) {
        return greenhouseRepository.findById(id);
    }

    public Greenhouse updateGreenhouse(Greenhouse greenhouse) {
        if (greenhouse.getId() == null || !greenhouseRepository.existsById(greenhouse.getId())) {
            throw new IllegalArgumentException("Greenhouse ID not found for update.");
        }
        return greenhouseRepository.save(greenhouse);
    }

    public void deleteGreenhouseById(Integer id) {
        greenhouseRepository.deleteById(id);
    }
}
