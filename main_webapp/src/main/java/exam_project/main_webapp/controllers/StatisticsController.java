package exam_project.main_webapp.controllers;

import exam_project.main_webapp.pojos.User;
import exam_project.main_webapp.repositories.UserRepository;
import exam_project.main_webapp.services.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class StatisticsController {
    private final StatisticsService statisticsService;

    @Autowired
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping("/statistiche")
    public String statistiche(Authentication authentication, Model model) {
        String username = authentication.getName();
        Map<String, Integer> stats = statisticsService.getTrainingsCountersByUsername(username);
        model.addAttribute("name", authentication.getName());
        model.addAttribute("statistics", stats);
        return "statistiche";
    }
}