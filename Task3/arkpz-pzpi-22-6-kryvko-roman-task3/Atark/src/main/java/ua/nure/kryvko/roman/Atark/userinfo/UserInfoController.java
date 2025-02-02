package ua.nure.kryvko.roman.Atark.userinfo;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ua.nure.kryvko.roman.Atark.user.User;
import ua.nure.kryvko.roman.Atark.user.UserService;

import java.util.Map;
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
    ResponseEntity<UserInfo> getById(@PathVariable Integer id) {
        try {
            return new ResponseEntity<>(userInfoService.getById(id), HttpStatus.OK);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{id}")
    @ResponseBody
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    ResponseEntity<UserInfo> getByUserId(@PathVariable Integer id) {
        Optional<User> userOptional = userService.getUserById(id);
        if(userOptional.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(userInfoService.getByUser(userOptional.get()), HttpStatus.OK);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<Map<String, String>> create(@Valid @RequestBody UserInfo userInfo) {
        try {
            if (userInfoService.existsByUser(userInfo.getUser())) {
                return new ResponseEntity<>(Map.of("error", "User info already exists!"), HttpStatus.CONFLICT);
            }
            userInfoService.createUserInfo(userInfo);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("error", "Error in input data"), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(Map.of("error", "Unexpected error occurred"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    ResponseEntity<Void> update(@Valid @RequestBody UserInfo userInfo, @PathVariable Integer id) {
        try {
            userInfoService.updateUserInfo(userInfo, id);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    ResponseEntity<Void> delete(@PathVariable Integer id) {
        try {
            userInfoService.deleteUserInfo(id);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
