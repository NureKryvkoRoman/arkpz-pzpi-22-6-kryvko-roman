package ua.nure.kryvko.roman.Atark.automationRuleDetails;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

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
        try {
            AutomationRuleDetails created = automationRuleDetailsService.createAutomationRuleDetails(automationRuleDetails);
            return ResponseEntity.status(HttpStatus.CREATED).body(created);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).build();
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
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

    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN') or #id == authentication.principal.id")
    public ResponseEntity<AutomationRuleDetails> updateAutomationRuleDetails(@PathVariable Integer id,
                                                                             @RequestBody AutomationRuleDetails automationRuleDetails) {
        try {
            AutomationRuleDetails updated = automationRuleDetailsService.updateAutomationRuleDetails(id, automationRuleDetails);
            return ResponseEntity.ok(updated);
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
    public ResponseEntity<Void> deleteAutomationRuleDetails(@PathVariable Integer id) {
        try {
            automationRuleDetailsService.deleteAutomationRuleDetails(id);
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
