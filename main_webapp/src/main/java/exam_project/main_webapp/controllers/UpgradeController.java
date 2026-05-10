package exam_project.main_webapp.controllers;

import exam_project.main_webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
        model.addAttribute("currentAuthority", currentAuthority);
        return "upgrade";
    }

    @PostMapping("/upgrade")
    public String upgrade(Authentication authentication,
                          @RequestParam String newPlan,
                          Model model) {
        String username = authentication.getName();
        userRepository.upgradeUser(username, newPlan);
        model.addAttribute("success", "Upgrade a " + newPlan + " effettuato con successo!");
        model.addAttribute("currentAuthority", newPlan);
        return "upgrade";
    }
}