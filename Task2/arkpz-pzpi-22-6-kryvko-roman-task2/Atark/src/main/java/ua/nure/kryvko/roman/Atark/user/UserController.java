package ua.nure.kryvko.roman.Atark.user;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Get all Users
     * @return
     */
    @GetMapping("")
    List<User> findAll() {
        return userService.getAllUsers();
    }

    /**
     * Get a user by ID
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    User findById(@PathVariable Integer id) {
        Optional<User> user = userService.getUserById(id);
        if(user.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return user.get();
    }

    /**
     * Add a new user
     * @param user
     */
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    void create(@Valid @RequestBody User user) {
        userService.saveUser(user);
    }

    /**
     * Edit info about a user
     * @param user
     * @param id
     */
    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void update(@Valid @RequestBody User user, @PathVariable Integer id) {
        userService.updateUser(user, id);
    }

    /**
     * Remove a user
     * @param id
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    void delete(@PathVariable Integer id) {
        userService.deleteUser(id);
    }

}
