package ua.nure.kryvko.roman.Atark.greenhouse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/greenhouses")
public class GreenhouseController {

    private final GreenhouseService greenhouseService;

    @Autowired
    public GreenhouseController(GreenhouseService greenhouseService) {
        this.greenhouseService = greenhouseService;
    }

    @PostMapping("")
    public ResponseEntity<Greenhouse> createGreenhouse(@RequestBody Greenhouse greenhouse) {
        try {
            Greenhouse savedGreenhouse = greenhouseService.saveGreenhouse(greenhouse);
            return new ResponseEntity<>(savedGreenhouse, HttpStatus.CREATED);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasRole('ADMIN') or #userId == authentication.principal.id")
    public ResponseEntity<List<Greenhouse>> getGreenhousesByUserId(@PathVariable Integer userId) {
        try {
            List<Greenhouse> greenhouses = greenhouseService.getGreenhousesByUserId(userId);
            return ResponseEntity.ok(greenhouses);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('ADMIN') or #email == authentication.name")
    public ResponseEntity<List<Greenhouse>> getGreenhousesByUserEmail(@RequestParam String email) {
        try {
            List<Greenhouse> greenhouses = greenhouseService.getGreenhousesByUserEmail(email);
            return ResponseEntity.ok(greenhouses);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Greenhouse> getGreenhouseById(@PathVariable Integer id) {
        Optional<Greenhouse> greenhouse = greenhouseService.getGreenhouseById(id);
        return greenhouse.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Greenhouse> updateGreenhouse(@PathVariable Integer id, @RequestBody Greenhouse greenhouse) {
        try {
            Greenhouse updatedGreenhouse = greenhouseService.updateGreenhouse(id, greenhouse);
            return ResponseEntity.ok(updatedGreenhouse);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Void> deleteGreenhouse(@PathVariable Integer id) {
        try {
            greenhouseService.deleteGreenhouseById(id);
            return ResponseEntity.noContent().build();
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
