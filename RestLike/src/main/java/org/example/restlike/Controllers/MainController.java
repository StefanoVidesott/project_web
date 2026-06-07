package org.example.restlike.Controllers;

import org.example.restlike.Pojos.Composition;
import org.example.restlike.Pojos.Training;
import org.example.restlike.Repository.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MainController {
    private final TrainingRepository trainingRepository;

    @Autowired
    public MainController(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @GetMapping("/trainings")
    public List<Training> getDefaultTrainings() {
        return trainingRepository.getDefaultTrainings();
    }

    @GetMapping("/exercises")
    public List<Composition> getExercisesByTrainingName(@RequestParam String name) {
        return trainingRepository.getExercisesByTrainingName(name);
    }

    @GetMapping("/exercise-names")
    public List<String> getExerciseNames() {
        return trainingRepository.getExerciseNames();
    }

    @PostMapping("/kcal")
    public double getKcal(@RequestBody List<Composition> compositions) {
        return trainingRepository.getKcal(compositions);
    }
}
