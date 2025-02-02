package ua.nure.kryvko.roman.Atark.userinfo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.nure.kryvko.roman.Atark.user.User;
import ua.nure.kryvko.roman.Atark.user.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/userinfo")
public class UserInfoController {
    private final UserInfoService userInfoService;
    private final UserService userService;

    public UserInfoController(UserInfoService userInfoService, UserService userService) {
        this.userInfoService = userInfoService;
        this.userService = userService;
    }

    //TODO: add methods to find userinfo by user email / login
    @GetMapping("/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    UserInfo getById(@PathVariable Integer id) {
        return userInfoService.getById(id);
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    UserInfo getByUserId(@PathVariable Integer id) {
        Optional<User> userOptional = userService.getUserById(id);
        if(userOptional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        return userInfoService.getByUser(userOptional.get());
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    void create(@Valid @RequestBody UserInfo userInfo) {
        userInfoService.createUserInfo(userInfo);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    void update(@Valid @RequestBody UserInfo userInfo, @PathVariable Integer id) {
        userInfoService.updateUserInfo(userInfo, id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    void delete(@PathVariable Integer id) {
        userInfoService.deleteUserInfo(id);
    }
}
