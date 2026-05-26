package exam_project.main_webapp.controllers;

import exam_project.main_webapp.repositories.TrainingRepository;
import exam_project.main_webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminDashboardController {
    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;

    @Autowired
    public AdminDashboardController(UserRepository userRepository, TrainingRepository trainingRepository) {
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
    }

    @GetMapping("/adminListaUtenti")
    public String listaUtenti(Authentication authentication, Model model) {
        model.addAttribute("name", authentication.getName());
        model.addAttribute("users", userRepository.findAllUsers());
        return "adminDashboard";
    }

    @PostMapping("/rimuoviScaduti")
    public String rimuoviScaduti(Authentication authentication, Model model) {
        int removed = userRepository.removeDisabledProvaUsers();
        model.addAttribute("name", authentication.getName());
        model.addAttribute("removed", removed);
        return "adminDashboard";
    }

    @GetMapping("/adminStatistiche")
    public String statistiche(Authentication authentication, Model model) {
        model.addAttribute("name", authentication.getName());
        model.addAttribute("statistics", trainingRepository.getStatisticsGroupedByTraining());
        return "adminDashboard";
    }
}