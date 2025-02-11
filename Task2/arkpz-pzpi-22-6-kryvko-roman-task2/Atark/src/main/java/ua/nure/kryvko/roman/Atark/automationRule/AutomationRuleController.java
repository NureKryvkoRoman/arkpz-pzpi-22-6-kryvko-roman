package ua.nure.kryvko.roman.Atark.automationRule;

import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automation-rules")
public class AutomationRuleController {

    private final AutomationRuleService automationRuleService;

    @Autowired
    public AutomationRuleController(AutomationRuleService automationRuleService) {
        this.automationRuleService = automationRuleService;
    }

    /**
     * Add a new AutomationRule to the system.
     * @param automationRule
     * @return
     */
    @PostMapping
    public ResponseEntity<AutomationRule> createAutomationRule(@RequestBody AutomationRule automationRule) {
        try {
            AutomationRule createdRule = automationRuleService.createAutomationRule(automationRule);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRule);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Retrieve all AutomationRules
     * @return
     */
    @GetMapping
    public ResponseEntity<List<AutomationRule>> getAllAutomationRules() {
        return ResponseEntity.ok(automationRuleService.getAllAutomationRules());
    }

    /**
     * Retrieve an AutomationRule by ID.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<AutomationRule> getAutomationRuleById(@PathVariable Integer id) {
        return automationRuleService.getAutomationRuleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    /**
     * Edit an AutomationRule data by ID.
     * @param id
     * @param automationRule
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<AutomationRule> updateAutomationRule(@PathVariable Integer id, @RequestBody AutomationRule automationRule) {
        try {
            AutomationRule updatedRule = automationRuleService.updateAutomationRule(id, automationRule);
            return ResponseEntity.ok(updatedRule);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    /**
     * Delete an AutomationRule by ID.
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutomationRule(@PathVariable Integer id) {
        try {
            automationRuleService.deleteAutomationRule(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
