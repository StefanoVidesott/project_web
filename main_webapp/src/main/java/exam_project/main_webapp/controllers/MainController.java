package exam_project.main_webapp.controllers;

import exam_project.main_webapp.pojos.User;
import exam_project.main_webapp.services.CheckUserService;
import exam_project.main_webapp.services.PasswordValidationService;
import exam_project.main_webapp.services.TrainingService;
import exam_project.main_webapp.services.UserService;
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
    private final UserService userService;
    private final CheckUserService checkUserService;
    private final PasswordValidationService passwordValidationService;
    private final TrainingService trainingService;

    @Autowired
    public MainController(UserService userService,
                          CheckUserService checkUserService,
                          PasswordValidationService passwordValidationService,
                          TrainingService trainingService) {
        this.userService = userService;
        this.checkUserService = checkUserService;
        this.passwordValidationService = passwordValidationService;
        this.trainingService = trainingService;
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
    @GetMapping("/contacts")
    public String contacts() {
        return "contacts";
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
            model.addAttribute("error", String.format("Username %s già esistente, sceglierne un altro.", username));
            return "signup";
        }

        if (!passwordValidationService.isValid(password)) {
            model.addAttribute("error", "La password deve essere lunga 8 caratteri e contenere 'id_07'.");
            return "signup";
        }

        userService.addUser(new User(firstName, lastName, email, username, password, plan, Date.valueOf(birthDate), null));
        model.addAttribute("username", username);
        return "signupSuccess";
    }

    // User's Dashboard Forwarding
    @GetMapping("/dashboard")
    public String dashboard(Authentication authentication) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))) {
            return "forward:/dashboard/admin";
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER_PROVA"))) {
            return "forward:/dashboard/trial";
        } else if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER_BASIC"))) {
            return "forward:/dashboard/basic";
        } else {
            return "forward:/dashboard/pro";
        }
    }

    // Admin Dashboard
    @GetMapping("/dashboard/admin")
    public String adminDashboard(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute("name", username);
        return "adminDashboard";
    }

    // Prova User Dashboard
    @GetMapping("/dashboard/trial")
    public String trialUserDashboard(Authentication authentication, Model model) {
        String username = authentication.getName();
        int trainingsCount = trainingService.getDefaultTrainingsCount(username);
        model.addAttribute("name", username);
        model.addAttribute("trainingsCount", trainingsCount);
        return "trialUserDashboard";
    }

    // Basic User Dashboard
    @GetMapping("/dashboard/basic")
    public String basicUserDashboard(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute("name", username);
        return "basicUserDashboard";
    }

    // Pro User Dashboard
    @GetMapping("/dashboard/pro")
    public String proUserDashboard(Authentication authentication, Model model) {
        String username = authentication.getName();
        model.addAttribute("name", username);
        return "proUserDashboard";
    }
}
