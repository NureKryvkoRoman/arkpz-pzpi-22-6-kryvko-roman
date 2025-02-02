package ua.nure.kryvko.roman.Atark.automationRuleDetails;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/automation-rule-details")
public class AutomationRuleDetailsController {

    private final AutomationRuleDetailsService automationRuleDetailsService;

    @Autowired
    public AutomationRuleDetailsController(AutomationRuleDetailsService automationRuleDetailsService) {
        this.automationRuleDetailsService = automationRuleDetailsService;
    }

    @PostMapping
    public ResponseEntity<AutomationRuleDetails> createAutomationRuleDetails(@RequestBody AutomationRuleDetails automationRuleDetails) {
        AutomationRuleDetails created = automationRuleDetailsService.createAutomationRuleDetails(automationRuleDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public List<AutomationRuleDetails> getAllAutomationRuleDetails() {
        return automationRuleDetailsService.getAllAutomationRuleDetails();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<AutomationRuleDetails> getAutomationRuleDetailsById(@PathVariable Integer id) {
        AutomationRuleDetails found = automationRuleDetailsService.getAutomationRuleDetailsById(id);
        return ResponseEntity.ok(found);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<AutomationRuleDetails> updateAutomationRuleDetails(@PathVariable Integer id,
                                                                             @RequestBody AutomationRuleDetails automationRuleDetails) {
        AutomationRuleDetails updated = automationRuleDetailsService.updateAutomationRuleDetails(id, automationRuleDetails);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<Void> deleteAutomationRuleDetails(@PathVariable Integer id) {
        automationRuleDetailsService.deleteAutomationRuleDetails(id);
        return ResponseEntity.noContent().build();
    }
}
