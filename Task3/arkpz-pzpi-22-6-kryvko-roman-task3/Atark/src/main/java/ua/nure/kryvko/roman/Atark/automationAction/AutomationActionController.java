package ua.nure.kryvko.roman.Atark.automationAction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automation-actions")
public class AutomationActionController {

    private final AutomationActionService automationActionService;

    public AutomationActionController(AutomationActionService automationActionService) {
        this.automationActionService = automationActionService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<AutomationAction> createAutomationAction(@RequestBody AutomationAction action) {
        try {
            AutomationAction createdAction = automationActionService.createAutomationAction(action);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<AutomationAction> getAutomationActionById(@PathVariable Integer id) {
        return automationActionService.getAutomationActionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Void> deleteAutomationAction(@PathVariable Integer id) {
        try {
            automationActionService.deleteAutomationAction(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<List<AutomationAction>> getAllAutomationActions() {
        return ResponseEntity.ok(automationActionService.getAllAutomationActions());
    }
}