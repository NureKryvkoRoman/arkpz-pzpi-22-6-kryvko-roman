package ua.nure.kryvko.roman.Atark.registration;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ua.nure.kryvko.roman.Atark.user.User;
import ua.nure.kryvko.roman.Atark.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ua.nure.kryvko.roman.Atark.user.UserRole;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtil jwtUtils;

    @PostMapping("/login")
    public String authenticateUser(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        user.getEmail(),
                        user.getPassword()
                )
        );
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return jwtUtils.generateToken(userDetails.getUsername());
    }

    //TODO: add username (login) existence checks
    @PostMapping("/signup")
    public ResponseEntity<String> registerUser(@RequestBody LoginRequest loginRequest) {
        if (userRepository.existsByEmail(loginRequest.getEmail())) {
            return new ResponseEntity<>("Error: email is already taken!", HttpStatus.CONFLICT);
        }
        // Create new user's account
        User newUser = new User(
                "placeholder", //TODO: change
                loginRequest.getEmail(),
                encoder.encode(loginRequest.getPassword()),
                UserRole.USER
        );
        userRepository.save(newUser);
        return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }
}