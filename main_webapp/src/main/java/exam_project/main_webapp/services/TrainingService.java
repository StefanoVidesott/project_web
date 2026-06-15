package exam_project.main_webapp.services;

import exam_project.main_webapp.pojos.Composition;
import exam_project.main_webapp.pojos.CustomComposition;
import exam_project.main_webapp.pojos.Training;
import exam_project.main_webapp.repositories.TrainingRepository;
import exam_project.main_webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TrainingService {
    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingService(UserRepository userRepository, TrainingRepository trainingRepository) {
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
    }

    public void incrementTrainingCounter(String username, int trainingId, boolean isDefault) {
        userRepository.incrementTrainingCounter(username, trainingId, isDefault);
    }

    public int getDefaultTrainingsCount(String username) {
        return userRepository.getDefaultTrainingsCount(username);
    }

    public List<Training> getCustomTrainingsByUsername(String username) {
        return trainingRepository.getCustomTrainingsByUsername(username);
    }

    public void enrichTrainingsWithKcal(List<Training> trainings) {
        trainingRepository.enrichTrainingsWithKcal(trainings);
    }

    public Training getCustomTrainingById(int id) {
        return trainingRepository.getCustomTrainingById(id);
    }

    public List<CustomComposition> getCustomExercisesByTrainingId(int id) {
        return trainingRepository.getCustomExercisesByTrainingId(id);
    }

    public boolean addCustomTraining(String username, String trainingName, List<Composition> exercises) {
        return trainingRepository.addCustomTraining(username, trainingName, exercises);
    }
}
