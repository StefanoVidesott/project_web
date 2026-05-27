package exam_project.main_webapp.services;

import exam_project.main_webapp.repositories.TrainingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {
    private final TrainingRepository trainingRepository;

    @Autowired
    public StatisticsService(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    public List<Map<String, Object>> getStatisticsByUsername(String username) {
        return trainingRepository.getStatisticsByUsername(username);
    }
}
