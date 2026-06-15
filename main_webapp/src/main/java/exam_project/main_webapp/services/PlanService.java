package exam_project.main_webapp.services;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PlanService {

    private static final List<String> ALLOWED_PLANS = List.of("ROLE_USER_PROVA", "ROLE_USER_BASIC", "ROLE_USER_PRO");

    public boolean isAllowedUserPlan(String newPlan) {
        return ALLOWED_PLANS.contains(newPlan);
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