package ua.nure.kryvko.roman.Atark.automationRule;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ua.nure.kryvko.roman.Atark.greenhouse.Greenhouse;
import ua.nure.kryvko.roman.Atark.greenhouse.GreenhouseRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AutomationRuleService {

    private final AutomationRuleRepository automationRuleRepository;
    private final GreenhouseRepository greenhouseRepository;

    @Autowired
    public AutomationRuleService(AutomationRuleRepository automationRuleRepository, GreenhouseRepository greenhouseRepository) {
        this.automationRuleRepository = automationRuleRepository;
        this.greenhouseRepository = greenhouseRepository;
    }

    @Transactional
    public AutomationRule createAutomationRule(AutomationRule automationRule) {
        Greenhouse owner = greenhouseRepository.findById(automationRule.getGreenhouse().getId())
                .orElseThrow(() -> new IllegalArgumentException("Greenhouse does not exist."));
        automationRule.setGreenhouse(owner);
        return automationRuleRepository.save(automationRule);
    }

    public List<AutomationRule> getAllAutomationRules() {
        return automationRuleRepository.findAll();
    }

    public Optional<AutomationRule> getAutomationRuleById(Integer id) {
        return automationRuleRepository.findById(id);
    }

    @Transactional
    public AutomationRule updateAutomationRule(Integer id, AutomationRule updatedAutomationRule) {
        updatedAutomationRule.setId(id);
        if (!automationRuleRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Automation Rule does not exist.");
        }

        Greenhouse owner = greenhouseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Greenhouse does not exist."));
        updatedAutomationRule.setGreenhouse(owner);

        return automationRuleRepository.save(updatedAutomationRule);
    }

    @Transactional
    public void deleteAutomationRule(Integer id) {
        if (!automationRuleRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Automation Rule does not exist.");
        }
        automationRuleRepository.deleteById(id);
    }
}
