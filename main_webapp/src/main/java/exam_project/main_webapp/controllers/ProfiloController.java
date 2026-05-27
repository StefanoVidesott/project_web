package exam_project.main_webapp.controllers;

import exam_project.main_webapp.pojos.User;
import exam_project.main_webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProfiloController {
    private final UserRepository userRepository;

    @Autowired
    public ProfiloController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/profilo")
    public String profilo(Authentication authentication, Model model) {
        User user = userRepository.findUserByUsername(authentication.getName());
        model.addAttribute("user", user);
        return ("profilo");
    }
}