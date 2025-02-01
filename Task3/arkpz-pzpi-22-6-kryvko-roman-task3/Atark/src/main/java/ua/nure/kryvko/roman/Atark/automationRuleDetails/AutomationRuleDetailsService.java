package ua.nure.kryvko.roman.Atark.automationRuleDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.nure.kryvko.roman.Atark.automationRule.AutomationRuleRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class AutomationRuleDetailsService {

    private final AutomationRuleDetailsRepository automationRuleDetailsRepository;
    private final AutomationRuleRepository automationRuleRepository;

    @Autowired
    public AutomationRuleDetailsService(AutomationRuleDetailsRepository automationRuleDetailsRepository,
                                        AutomationRuleRepository automationRuleRepository) {
        this.automationRuleDetailsRepository = automationRuleDetailsRepository;
        this.automationRuleRepository = automationRuleRepository;
    }

    public AutomationRuleDetails createAutomationRuleDetails(AutomationRuleDetails automationRuleDetails) {
        if (!automationRuleRepository.existsById(automationRuleDetails.getAutomationRule().getId())) {
            throw new IllegalArgumentException("AutomationRule does not exist.");
        }
        return automationRuleDetailsRepository.save(automationRuleDetails);
    }

    public List<AutomationRuleDetails> getAllAutomationRuleDetails() {
        return automationRuleDetailsRepository.findAll();
    }

    public AutomationRuleDetails getAutomationRuleDetailsById(Integer id) {
        return automationRuleDetailsRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("AutomationRuleDetails not found."));
    }

    public AutomationRuleDetails updateAutomationRuleDetails(Integer id, AutomationRuleDetails automationRuleDetails) {
        if (!automationRuleDetailsRepository.existsById(id)) {
            throw new NoSuchElementException("AutomationRuleDetails not found.");
        }
        if (!automationRuleRepository.existsById(automationRuleDetails.getAutomationRule().getId())) {
            throw new IllegalArgumentException("AutomationRule does not exist.");
        }
        automationRuleDetails.setId(id);
        return automationRuleDetailsRepository.save(automationRuleDetails);
    }

    public void deleteAutomationRuleDetails(Integer id) {
        if (!automationRuleDetailsRepository.existsById(id)) {
            throw new NoSuchElementException("AutomationRuleDetails not found.");
        }
        automationRuleDetailsRepository.deleteById(id);
    }
}
