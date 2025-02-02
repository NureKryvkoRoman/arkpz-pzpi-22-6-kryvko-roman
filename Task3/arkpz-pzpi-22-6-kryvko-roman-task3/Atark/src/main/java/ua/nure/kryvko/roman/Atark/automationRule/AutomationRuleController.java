package ua.nure.kryvko.roman.Atark.automationRule;

import org.springframework.security.access.prepost.PreAuthorize;
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

    @PostMapping
    public ResponseEntity<AutomationRule> createAutomationRule(@RequestBody AutomationRule automationRule) {
        try {
            AutomationRule createdRule = automationRuleService.createAutomationRule(automationRule);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdRule);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<List<AutomationRule>> getAllAutomationRules() {
        return ResponseEntity.ok(automationRuleService.getAllAutomationRules());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<AutomationRule> getAutomationRuleById(@PathVariable Integer id) {
        return automationRuleService.getAutomationRuleById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<AutomationRule> updateAutomationRule(@PathVariable Integer id, @RequestBody AutomationRule automationRule) {
        try {
            AutomationRule updatedRule = automationRuleService.updateAutomationRule(id, automationRule);
            return ResponseEntity.ok(updatedRule);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Void> deleteAutomationRule(@PathVariable Integer id) {
        try {
            automationRuleService.deleteAutomationRule(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
