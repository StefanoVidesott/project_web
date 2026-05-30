package exam_project.main_webapp.controllers;

import exam_project.main_webapp.services.PasswordValidationService;
import exam_project.main_webapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordController {
    private final UserService userService;
    private final PasswordValidationService passwordValidationService;

    @Autowired
    public PasswordController(UserService userService, PasswordValidationService passwordValidationService) {
        this.userService = userService;
        this.passwordValidationService = passwordValidationService;
    }

    @GetMapping("/cambioPassword")
    public String cambioPasswordPage() {
        return "cambioPassword";
    }

    @PostMapping("/cambioPassword")
    public String cambioPassword(Authentication authentication, @RequestParam String oldPassword, @RequestParam String newPassword, Model model) {

        // Verifiche aggiuntiva lato backend su formato password
        if (!passwordValidationService.isValid(newPassword)) {
            model.addAttribute("error", "La password deve essere lunga 8 caratteri e contenere 'id_07'.");
            return "cambioPassword";
        }

        String username = authentication.getName();
        boolean success = userService.changePassword(username, oldPassword, newPassword);

        if (success) {
            model.addAttribute("success", "Password aggiornata con successo.");
        }
        else {
            model.addAttribute("error", "La vecchia password non è corretta.");
        }

        return "cambioPassword";
    }
}
