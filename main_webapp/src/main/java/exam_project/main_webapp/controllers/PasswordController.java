package exam_project.main_webapp.controllers;

import exam_project.main_webapp.pojos.User;
import exam_project.main_webapp.repositories.UserRepository;
import exam_project.main_webapp.services.PasswordValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PasswordController {
    private final UserRepository userRepository;
    private final PasswordValidationService passwordValidationService;

    @Autowired
    public PasswordController(UserRepository userRepository, PasswordValidationService passwordValidationService) {
        this.userRepository = userRepository;
        this.passwordValidationService = passwordValidationService;
    }

    @GetMapping("/cambioPassword")
    public String cambioPasswordPage() {
        return "cambioPassword";
    }

    @PostMapping("/cambioPassword")
    public String cambioPassword(Authentication authentication,
                                 @RequestParam String oldPassword,
                                 @RequestParam String newPassword,
                                 Model model) {

        // Verifiche aggiuntive lato backend sul formato della password e legittimità del cambio
        if (!passwordValidationService.isValid(newPassword)) {
            model.addAttribute("error", "La password deve essere lunga almeno 8 caratteri e contenere 'id_07'.");
            return "cambioPassword";
        }

        String username = authentication.getName();
        boolean success = userRepository.changePassword(username, oldPassword, newPassword);

        if (!success) {
            model.addAttribute("error", "La vecchia password non è corretta.");
            return "cambioPassword";
        }

        model.addAttribute("success", "Password aggiornata con successo.");
        return "cambioPassword";
    }
}
