package exam_project.main_webapp.services;

import exam_project.main_webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TrainingService {
    private final UserRepository userRepository;

    @Autowired
    public TrainingService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void incrementTrainingCounter(String username, int trainingId, boolean isDefault) {
        userRepository.incrementTrainingCounter(username, trainingId, isDefault);
    }

    public int getDefaultTrainingsCount(String username) {
        return userRepository.getDefaultTrainingsCount(username);
    }
}
