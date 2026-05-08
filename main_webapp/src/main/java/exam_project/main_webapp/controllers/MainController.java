package exam_project.main_webapp.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MainController {

    // Home page
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // Login Page
    @GetMapping("/login")
    public String login() {
        return "login"; // Spring Security Login (lezione 06/05)
    }

    // Forward failureForwardUrl (POST)
    @PostMapping("/loginFailure")
    public String loginFailure() {
        return "loginFailure";
    }

    // Logout Page
    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    // Signing up
    // @GetMapping("/signup")
    // public String signup() {
    //     return "signup";
    // }

    // @PostMapping("/addUser")
    // TODO

    // User's Dashboard Forwarding
    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "forward:adminDashboard";
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER_PROVA"))) {
            return "forward:provaUserDashboard";
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER_BASIC"))) {
            return "forward:basicUserDashboard";
        } else {
            return "forward:proUserDashboard";
        }
    }

    // Admin Dashboard
    @GetMapping("/adminDashboard")
    public String adminDashboard(Authentication authentication, Model model) {
        model.addAttribute("name", authentication.getName());
        return "adminDashboard";
    }

    // Prova User Dashboard
    @GetMapping("/provaUserDashboard")
    public String provaUserDashboard(Authentication authentication, Model model) {
        model.addAttribute("name", authentication.getName());
        return "provaUserDashboard";
    }

    // basic User Dashboard
    @GetMapping("/basicUserDashboard")
    public String basicUserDashboard(Authentication authentication, Model model) {
        model.addAttribute("name", authentication.getName());
        return "basicUserDashboard";
    }

    // Pro User Dashboard
    @GetMapping("/proUserDashboard")
    public String proUserDashboard(Authentication authentication, Model model) {
        model.addAttribute("name", authentication.getName());
        return "proUserDashboard";
    }
}