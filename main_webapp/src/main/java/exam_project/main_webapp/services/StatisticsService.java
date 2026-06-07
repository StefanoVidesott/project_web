package exam_project.main_webapp.services;

import exam_project.main_webapp.repositories.TrainingRepository;
import exam_project.main_webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {
    private final UserRepository userRepository;
    private final TrainingRepository trainingRepository;

    @Autowired
    public StatisticsService(UserRepository userRepository, TrainingRepository trainingRepository) {
        this.userRepository = userRepository;
        this.trainingRepository = trainingRepository;
    }

    public List<Map<String, Object>> getAdminStatistics() {
        return trainingRepository.getAdminStatistics();
    }

    public Map<String, Integer> getTrainingsCountersByUsername(String username) {
        return userRepository.getTrainingsCountersByUsername(username);
    }
}
