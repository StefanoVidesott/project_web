package exam_project.main_webapp.controllers;

import exam_project.main_webapp.pojos.User;
import exam_project.main_webapp.services.StatisticsService;
import exam_project.main_webapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Map;

@Controller
public class AdminDashboardController {
    private final UserService userService;
    private final StatisticsService statisticsService;

    @Autowired
    public AdminDashboardController(UserService userService, StatisticsService statisticsService) {
        this.userService = userService;
        this.statisticsService = statisticsService;
    }

    @GetMapping("/admin/users")
    public String listUsers(Authentication authentication, Model model) {
        String username = authentication.getName();
        List<User> usersList = userService.findAllUsers();

        model.addAttribute("name", username);
        model.addAttribute("users", usersList);
        return "adminDashboard";
    }

    @PostMapping("/admin/remove-expired")
    public String removeExpiredUsers(Authentication authentication, Model model) {
        String username = authentication.getName();
        int removedUsers = userService.deleteExpiredTrialUsers();

        model.addAttribute("name", username);
        model.addAttribute("removed", removedUsers);
        return "adminDashboard";
    }

    @GetMapping("/admin/statistics")
    public String adminStatistics(Authentication authentication, Model model) {
        String username = authentication.getName();
        List<Map<String, Object>> adminStatistics = statisticsService.getAdminStatistics();

        model.addAttribute("name", username);
        model.addAttribute("statistics", adminStatistics);
        return "adminDashboard";
    }
}