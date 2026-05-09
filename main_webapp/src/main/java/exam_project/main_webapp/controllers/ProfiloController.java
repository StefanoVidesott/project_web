package exam_project.main_webapp.controllers;

import exam_project.main_webapp.pojos.User;
import exam_project.main_webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProfiloController {
    private final UserRepository userRepository;

    @Autowired
    public ProfiloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profilo")
    public String profilo(Authentication authentication, Model model) {
        String username = authentication.getName();
        User user = userRepository.findUserByUsername(username);
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        model.addAttribute("user", user);
        model.addAttribute("role", role);
        return ("profilo");
    }
}