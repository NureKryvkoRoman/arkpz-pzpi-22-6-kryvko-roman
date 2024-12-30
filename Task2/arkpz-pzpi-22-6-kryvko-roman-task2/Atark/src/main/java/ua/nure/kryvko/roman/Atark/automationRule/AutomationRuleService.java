package ua.nure.kryvko.roman.Atark.automationRule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public AutomationRule createAutomationRule(AutomationRule automationRule) {
        if (!greenhouseRepository.existsById(automationRule.getGreenhouse().getId())) {
            throw new IllegalArgumentException("Greenhouse does not exist.");
        }
        return automationRuleRepository.save(automationRule);
    }

    public List<AutomationRule> getAllAutomationRules() {
        return automationRuleRepository.findAll();
    }

    public Optional<AutomationRule> getAutomationRuleById(Integer id) {
        return automationRuleRepository.findById(id);
    }

    public AutomationRule updateAutomationRule(Integer id, AutomationRule updatedAutomationRule) {
        if (!automationRuleRepository.existsById(id)) {
            throw new IllegalArgumentException("Automation Rule does not exist.");
        }

        if (!greenhouseRepository.existsById(updatedAutomationRule.getGreenhouse().getId())) {
            throw new IllegalArgumentException("Greenhouse does not exist.");
        }

        updatedAutomationRule.setId(id);
        return automationRuleRepository.save(updatedAutomationRule);
    }

    public void deleteAutomationRule(Integer id) {
        if (!automationRuleRepository.existsById(id)) {
            throw new IllegalArgumentException("Automation Rule does not exist.");
        }
        automationRuleRepository.deleteById(id);
    }
}
