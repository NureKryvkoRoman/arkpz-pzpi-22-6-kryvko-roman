package ua.nure.kryvko.roman.Atark.automationRuleDetails;

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

    /**
     * Add new AutomationRuleDetails to the system.
     * @param automationRuleDetails
     * @return
     */
    @PostMapping
    public ResponseEntity<AutomationRuleDetails> createAutomationRuleDetails(@RequestBody AutomationRuleDetails automationRuleDetails) {
        AutomationRuleDetails created = automationRuleDetailsService.createAutomationRuleDetails(automationRuleDetails);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    /**
     * Get all AutomationRuleDetails
     * @return
     */
    @GetMapping
    public List<AutomationRuleDetails> getAllAutomationRuleDetails() {
        return automationRuleDetailsService.getAllAutomationRuleDetails();
    }

    /**
     * Get an AutomationRuleDetails by ID.
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<AutomationRuleDetails> getAutomationRuleDetailsById(@PathVariable Integer id) {
        AutomationRuleDetails found = automationRuleDetailsService.getAutomationRuleDetailsById(id);
        return ResponseEntity.ok(found);
    }

    /**
     * Edit info about AutomationRuleDetails by ID.
     * @param id
     * @param automationRuleDetails
     * @return
     */
    @PutMapping("/{id}")
    public ResponseEntity<AutomationRuleDetails> updateAutomationRuleDetails(@PathVariable Integer id,
                                                                             @RequestBody AutomationRuleDetails automationRuleDetails) {
        AutomationRuleDetails updated = automationRuleDetailsService.updateAutomationRuleDetails(id, automationRuleDetails);
        return ResponseEntity.ok(updated);
    }

    /**
     * Delete an AutomationRuleDetails by ID
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAutomationRuleDetails(@PathVariable Integer id) {
        automationRuleDetailsService.deleteAutomationRuleDetails(id);
        return ResponseEntity.noContent().build();
    }
}
