package exam_project.main_webapp.controllers;

import exam_project.main_webapp.pojos.User;
import exam_project.main_webapp.repositories.UserRepository;
import exam_project.main_webapp.services.CheckUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;

@Controller
public class MainController {
    private final UserRepository userRepository;
    private final CheckUserService checkUserService;

    @Autowired
    public MainController(UserRepository userRepository,
                          CheckUserService checkUserService) {
        this.userRepository = userRepository;
        this.checkUserService = checkUserService;
    }

    // Home page
    @GetMapping("/index")
    public String index() {
        return "index";
    }

    // Login Page
    @GetMapping("/login")
    public String login(@RequestParam(value = "loginError", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "#07: That user is not authenticated!");
        }
        return "login"; // Spring Security Login (lezione 06/05)
    }

    // Logout Page
    @GetMapping("/logout")
    public String logout() {
        return "logout";
    }

    // Contact page
    @GetMapping("/contatti")
    public String contacts() {
        return "contatti";
    }

    // Signing up
    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @PostMapping("/addUser")
    public String addUser(@RequestParam String firstName,
                          @RequestParam String lastName,
                          @RequestParam String birthDate,
                          @RequestParam String email,
                          @RequestParam String username,
                          @RequestParam String password,
                          @RequestParam String plan,
                          Model model) {

        if (checkUserService.userExists(username)) {
            model.addAttribute("username", username);
            model.addAttribute("error", "Username già esistente, sceglierne un altro.");
            return "signup";
        }

        // TODO Verificare se fare verifiche lato backend sono richieste (eg. password valida)

        userRepository.addUser(new User(firstName, lastName, email, username, password, plan, Date.valueOf(birthDate), null));

        model.addAttribute("username", username);
        return "signupSuccess";
    }

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