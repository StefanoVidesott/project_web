package exam_project.main_webapp.controllers;

import exam_project.main_webapp.repositories.UserRepository;
import exam_project.main_webapp.services.PlanService;
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
    private final PlanService planService;

    @Autowired
    public UpgradeController(UserRepository userRepository, PlanService planService) {
        this.userRepository = userRepository;
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
    public String upgrade(Authentication authentication,
                          @RequestParam String newPlan,
                          Model model) {
        String currentAuthority = authentication.getAuthorities().iterator().next().getAuthority();
        model.addAttribute("currentAuthority", currentAuthority);
        model.addAttribute("currentPlan", planService.getPlanName(currentAuthority));

        if (!planService.isValidUpgrade(newPlan)) {
            model.addAttribute("error", "Piano non valido.");
            return "upgrade";
        }

        userRepository.upgradeUser(authentication.getName(), newPlan);
        model.addAttribute("success", true);
        model.addAttribute("currentAuthority", newPlan);
        model.addAttribute("currentPlan", planService.getPlanName(newPlan));
        return "upgrade";
    }
}