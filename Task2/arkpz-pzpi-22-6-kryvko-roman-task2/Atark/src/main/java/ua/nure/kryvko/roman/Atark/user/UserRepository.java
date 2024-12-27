package ua.nure.kryvko.roman.Atark.user;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepository {
    private List<User> users = new ArrayList<>();

    public List<User> findAll() {
        return users;
    }

    public Optional<User> findById(int id) {
        return users.stream()
                .filter(user -> user.id() == id)
                .findFirst();
    }

    public void create(User user) {
        users.add(user);
    }

    public boolean update(User user, int id) {
        Optional<User> existingUser = findById(id);
        if (existingUser.isPresent()) {
            users.set(users.indexOf(id), user);
            return true;
        }
        return false;
    }

    public boolean delete(int id) {
        return users.removeIf(user -> user.id() == id);
    }
}
