package exam_project.main_webapp.controllers;

import exam_project.main_webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

@Controller
public class UpgradeController {
    private final UserRepository userRepository;

    @Autowired
    public UpgradeController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/upgrade")
    public String upgradePage(Authentication authentication, Model model) {
        String currentAuthority = authentication.getAuthorities().iterator().next().getAuthority();
        String currentPlan = switch (currentAuthority) {
            case "ROLE_USER_PROVA" -> "Prova";
            case "ROLE_USER_BASIC" -> "Basic";
            case "ROLE_USER_PRO" -> "Pro";
            case null, default -> "Error";
        };

        model.addAttribute("currentAuthority", currentAuthority);
        model.addAttribute("currentPlan", currentPlan);
        return "upgrade";
    }

    @PostMapping("/upgrade")
    public String upgrade(Authentication authentication,
                          @RequestParam String newPlan,
                          Model model) {
        List<String> allowedPlans = List.of("ROLE_USER_BASIC", "ROLE_USER_PRO");

        String currentAuthority = authentication.getAuthorities().iterator().next().getAuthority();
        String currentPlan = switch (currentAuthority) {
            case "ROLE_USER_PROVA" -> "Prova";
            case "ROLE_USER_BASIC" -> "Basic";
            case "ROLE_USER_PRO" -> "Pro";
            case null, default -> "Error";
        };

        model.addAttribute("currentAuthority", currentAuthority);
        model.addAttribute("currentPlan", currentPlan);

        if (!allowedPlans.contains(newPlan)) {
            model.addAttribute("error", "Piano non valido.");
            return "upgrade";
        }

        String username = authentication.getName();
        userRepository.upgradeUser(username, newPlan);
        currentPlan = switch (newPlan) {
            case "ROLE_USER_PROVA" -> "Prova";
            case "ROLE_USER_BASIC" -> "Basic";
            case "ROLE_USER_PRO" -> "Pro";
            case null, default -> "Error";
        };
        model.addAttribute("success", true);
        model.addAttribute("currentAuthority", newPlan);
        model.addAttribute("currentPlan", currentPlan);
        return "upgrade";
    }
}