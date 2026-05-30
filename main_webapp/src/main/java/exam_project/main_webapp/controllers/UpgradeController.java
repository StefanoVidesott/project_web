package exam_project.main_webapp.controllers;

import exam_project.main_webapp.services.PlanService;
import exam_project.main_webapp.services.UserService;
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
    private final UserService userService;
    private final PlanService planService;

    @Autowired
    public UpgradeController(UserService userService, PlanService planService) {
        this.userService = userService;
        this.planService = planService;
    }

    @GetMapping("/upgrade")
    public String upgradePage(Authentication authentication, Model model) {
        String currentAuthority = authentication.getAuthorities().iterator().next().getAuthority();
        model.addAttribute("currentAuthority", currentAuthority);
        model.addAttribute("currentPlan", planService.getPlanName(currentAuthority));
        return "upgrade";
    }

    @PostMapping("/upgrade")
    public String upgrade(Authentication authentication, @RequestParam String newPlan, Model model) {
        String username = authentication.getName();
        String currentAuthority = authentication.getAuthorities().iterator().next().getAuthority();
        model.addAttribute("currentAuthority", currentAuthority);
        model.addAttribute("currentPlan", planService.getPlanName(currentAuthority));

        // Verifica per evitare role escalation
        if (!planService.isValidUpgrade(newPlan)) {
            model.addAttribute("error", "Piano non valido.");
            return "upgrade";
        }

        userService.upgradeUser(username, newPlan);
        model.addAttribute("success", true);
        model.addAttribute("newPlan", planService.getPlanName(newPlan));
        return "upgrade";
    }
}