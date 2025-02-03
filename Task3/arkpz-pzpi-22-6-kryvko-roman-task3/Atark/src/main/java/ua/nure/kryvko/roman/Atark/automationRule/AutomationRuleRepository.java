package ua.nure.kryvko.roman.Atark.automationRule;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.nure.kryvko.roman.Atark.greenhouse.Greenhouse;

import java.util.List;

public interface AutomationRuleRepository extends JpaRepository<AutomationRule, Integer> {
    List<AutomationRule> findByGreenhouse(Greenhouse greenhouse);
}
