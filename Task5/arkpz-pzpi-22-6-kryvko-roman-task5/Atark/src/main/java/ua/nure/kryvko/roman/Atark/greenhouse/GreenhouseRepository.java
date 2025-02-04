package ua.nure.kryvko.roman.Atark.greenhouse;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.kryvko.roman.Atark.user.User;

import java.util.List;

public interface GreenhouseRepository extends JpaRepository<Greenhouse, Integer> {
    List<Greenhouse> findByUser(User user);
}
