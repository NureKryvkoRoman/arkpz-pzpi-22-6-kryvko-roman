package ua.nure.kryvko.roman.Atark.automationAction;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automation-actions")
public class AutomationActionController {

    private final AutomationActionService automationActionService;

    public AutomationActionController(AutomationActionService automationActionService) {
        this.automationActionService = automationActionService;
    }

    /**
     * Create a new AutomationAction for a greenhouse.
     * @param action
     * @return
     */
    @PostMapping
    public ResponseEntity<AutomationAction> createAutomationAction(@RequestBody AutomationAction action) {
        try {
            AutomationAction createdAction = automationActionService.createAutomationAction(action);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAction);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Get AutomationAction by its ID.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<AutomationAction> getAutomationActionById(@PathVariable Integer id) {
        return automationActionService.getAutomationActionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    /**
     * Remove AutomationRule from the system by ID.
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutomationAction(@PathVariable Integer id) {
        try {
            automationActionService.deleteAutomationAction(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    /**
     * Retrieve all AutomationActions
     * @return
     */
    @GetMapping
    public ResponseEntity<List<AutomationAction>> getAllAutomationActions() {
        return ResponseEntity.ok(automationActionService.getAllAutomationActions());
    }
}