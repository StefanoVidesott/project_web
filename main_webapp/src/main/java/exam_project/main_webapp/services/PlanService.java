package exam_project.main_webapp.services;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlanService {

    private static final List<String> ALLOWED_UPGRADES = List.of("ROLE_USER_BASIC", "ROLE_USER_PRO");

    public boolean isValidUpgrade(String newPlan) {
        return ALLOWED_UPGRADES.contains(newPlan);
    }

    public String getPlanName(String authority) {
        return switch (authority) {
            case "ROLE_USER_PROVA" -> "Prova";
            case "ROLE_USER_BASIC" -> "Basic";
            case "ROLE_USER_PRO" -> "Pro";
            case "ROLE_ADMIN" -> "Admin";
            default -> "Sconosciuto";
        };
    }
}