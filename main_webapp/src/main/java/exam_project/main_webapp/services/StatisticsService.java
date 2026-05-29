package exam_project.main_webapp.services;

import exam_project.main_webapp.repositories.AllenamentoRepository;
import exam_project.main_webapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticsService {
    private final UserRepository userRepository;
    private final AllenamentoRepository allenamentoRepository;

    @Autowired
    public StatisticsService(UserRepository userRepository, AllenamentoRepository allenamentoRepository) {
        this.userRepository = userRepository;
        this.allenamentoRepository = allenamentoRepository;
    }

    public List<Map<String, Object>> getAdminStatistics() {
        return allenamentoRepository.getAdminStatistics();
    }

    public Map<String, Integer> getTrainingsCountersByUsername(String username) {
        return userRepository.getTrainingsCountersByUsername(username);
    }
}
