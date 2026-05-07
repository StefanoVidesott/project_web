package gvarni.unitn.esempio_security.controllers;

import gvarni.unitn.esempio_security.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminDashboardController {
    private final UserRepository userRepository;

    @Autowired
    AdminDashboardController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/getUsers")
    public String getUsers(Authentication authentication, Model model) {
        String name = authentication.getName();
        model.addAttribute("name",name);
        model.addAttribute("users", userRepository.findAllUsers());
        return ("adminDashboard");}


}
