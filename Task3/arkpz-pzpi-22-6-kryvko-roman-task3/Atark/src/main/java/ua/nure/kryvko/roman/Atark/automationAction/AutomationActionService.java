package ua.nure.kryvko.roman.Atark.automationAction;

import org.springframework.stereotype.Service;
import ua.nure.kryvko.roman.Atark.automationRule.AutomationRuleRepository;
import ua.nure.kryvko.roman.Atark.controller.ControllerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class AutomationActionService {

    private final AutomationActionRepository automationActionRepository;
    private final AutomationRuleRepository automationRuleRepository;
    private final ControllerRepository controllerRepository;

    public AutomationActionService(AutomationActionRepository automationActionRepository,
                                   AutomationRuleRepository automationRuleRepository,
                                   ControllerRepository controllerRepository) {
        this.automationActionRepository = automationActionRepository;
        this.automationRuleRepository = automationRuleRepository;
        this.controllerRepository = controllerRepository;
    }

    public AutomationAction createAutomationAction(AutomationAction action) {
        if (!automationRuleRepository.existsById(action.getAutomationRule().getId())) {
            throw new IllegalArgumentException("Automation rule does not exist.");
        }

        if (!controllerRepository.existsById(action.getController().getId())) {
            throw new IllegalArgumentException("Controller does not exist.");
        }

        return automationActionRepository.save(action);
    }

    public Optional<AutomationAction> getAutomationActionById(Integer id) {
        return automationActionRepository.findById(id);
    }

    public void deleteAutomationAction(Integer id) {
        if (!automationActionRepository.existsById(id)) {
            throw new IllegalArgumentException("Automation action does not exist.");
        }
        automationActionRepository.deleteById(id);
    }

    public List<AutomationAction> getAllAutomationActions() {
        return automationActionRepository.findAll();
    }
}
