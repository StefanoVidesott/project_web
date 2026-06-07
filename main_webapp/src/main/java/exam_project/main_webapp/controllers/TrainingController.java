package exam_project.main_webapp.controllers;

import exam_project.main_webapp.pojos.ExerciseDTO;
import exam_project.main_webapp.proxies.TrainingsProxy;
import exam_project.main_webapp.pojos.Composition;
import exam_project.main_webapp.pojos.CustomComposition;
import exam_project.main_webapp.pojos.Training;
import exam_project.main_webapp.repositories.TrainingRepository;
import exam_project.main_webapp.repositories.UserRepository;
import exam_project.main_webapp.services.TrainingService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class TrainingController {
    private final TrainingsProxy trainingsProxy;
    private final TrainingRepository trainingRepository;
    private final UserRepository userRepository;
    private final TrainingService trainingService;

    public TrainingController(TrainingsProxy trainingsProxy, TrainingRepository trainingRepository, UserRepository userRepository, TrainingService trainingService) {
        this.trainingsProxy = trainingsProxy;
        this.trainingRepository = trainingRepository;
        this.userRepository = userRepository;
        this.trainingService = trainingService;
    }

    @GetMapping("/trainings")
    public String redirectToTrainings(Authentication authentication) {
        if (authentication.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_USER_PRO"))) {
            return "forward:/trainings/pro";
        } else {
            return "forward:/trainings/default";
        }
    }

    @GetMapping("/trainings/pro")
    public String getProTrainings(Model model, Authentication authentication) {
        String username = authentication.getName();
        List<Training> trainings = trainingsProxy.getDefaultTrainings();
        List<Training> customTrainings = trainingRepository.getCustomTrainingsByUsername(username);
        trainingRepository.enrichTrainingsWithKcal(trainings);
        model.addAttribute("trainings", trainings);
        model.addAttribute("customTrainings", customTrainings);
        return "proTrainings";
    }

    @GetMapping("/trainings/default")
    public String getDefaultTrainings(Model model) {
        List<Training> trainings = trainingsProxy.getDefaultTrainings();
        trainingRepository.enrichTrainingsWithKcal(trainings);
        model.addAttribute("trainings", trainings);
        return "defaultTrainings";
    }

    @GetMapping("/composition")
    public String getComposition(@RequestParam String trainingName, Model model) {
        List<Composition> composition = trainingsProxy.getExercisesByTrainingName(trainingName);
        model.addAttribute("composition", composition);
        return "composition";
    }

    @GetMapping("/composition/custom")
    public String getCustomComposition(@RequestParam int id, Model model) {
        List<CustomComposition> customComposition = trainingRepository.getCustomExercisesByTrainingId(id);
        model.addAttribute("customComposition", customComposition);
        return "customComposition";
    }

    @GetMapping("/training/custom")
    public String customTrainingForm(Model model) {
        List<String> exerciseNames = trainingsProxy.getExerciseNames();
        model.addAttribute("exerciseNames", exerciseNames);
        return "customTraining";
    }

    @PostMapping("/training/custom/save")
    public String saveCustomTraining(Authentication authentication, String trainingName, ExerciseDTO exercises, Model model) {
        String username = authentication.getName();
        boolean result = trainingRepository.addCustomTraining(username, trainingName, exercises.getExercises());
        if (!result) {
            model.addAttribute("error", "A training with the same name already exists.");
        }
        return "customTrainingSaved";
    }

    @PostMapping("/training/complete")
    public String completeTraining(@RequestParam int trainingId, @RequestParam boolean isDefault, Authentication authentication) {
        String username = authentication.getName();
        String role = authentication.getAuthorities().iterator().next().getAuthority();

        trainingService.incrementTrainingCounter(username, trainingId, isDefault);

        if (role.equals("ROLE_USER_PROVA")) {
            int total = userRepository.getDefaultTrainingsCount(username);
            if (total >= 3) {
                userRepository.disableUser(username);
                return "redirect:/perform_logout";
            }
        }

        return "redirect:/dashboard";
    }
}
