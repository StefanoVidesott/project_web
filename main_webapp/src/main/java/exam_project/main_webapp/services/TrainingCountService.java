package exam_project.main_webapp.services;

import exam_project.main_webapp.repositories.TrainingRepository;
import exam_project.main_webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TrainingCountService {
    private final TrainingRepository trainingRepository;

    @Autowired
    public TrainingCountService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public int countTrainingsByUsername(String username) {
        return trainingRepository.countTrainingsByUsername(username);
    }
}


