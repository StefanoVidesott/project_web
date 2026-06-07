package exam_project.main_webapp.repositories;

import exam_project.main_webapp.mappers.ExerciseMapper;
import exam_project.main_webapp.mappers.TrainingMapper;
import exam_project.main_webapp.proxies.TrainingsProxy;
import exam_project.main_webapp.pojos.Composition;
import exam_project.main_webapp.pojos.CustomComposition;
import exam_project.main_webapp.pojos.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Repository
public class TrainingRepository {
    private final JdbcTemplate jdbc;
    private final TrainingsProxy trainingsProxy;

    @Autowired
    public TrainingRepository(JdbcTemplate jdbc, TrainingsProxy trainingsProxy) {
        this.jdbc = jdbc;
        this.trainingsProxy = trainingsProxy;
    }

    public void enrichTrainingsWithKcal(List<Training> trainings) {
        for (Training t : trainings) {
            List<Composition> exercises = trainingsProxy.getExercisesByTrainingName(t.getName());
            t.setKcal(trainingsProxy.getKcal(exercises));
        }
    }

    public List<Training> getCustomTrainingsByUsername(String username) {
        String sql = "SELECT * FROM CUSTOM_TRAININGS WHERE username = ?";
        return jdbc.query(sql, new TrainingMapper(), username);
    }

    public List<CustomComposition> getCustomExercisesByTrainingId(int id) {
        String sql = "SELECT * FROM EXERCISES WHERE training_id = ?";
        return jdbc.query(sql, new ExerciseMapper(), id);
    }

    @Transactional
    public boolean addCustomTraining(String username, String trainingName, List<Composition> exercises) {
        List<Training> customTrainings = getCustomTrainingsByUsername(username);
        for (Training training : customTrainings) {
            if (Objects.equals(training.getName(), trainingName)) { return false; }
        }

        String sqlTrainings = "INSERT INTO CUSTOM_TRAININGS (name, kcal, username) VALUES (?, ?, ?)";
        jdbc.update(sqlTrainings, trainingName, trainingsProxy.getKcal(exercises), username);

        String sqlId = "SELECT MAX(id) FROM CUSTOM_TRAININGS WHERE name = ?";
        Long newTrainingId = jdbc.queryForObject(sqlId, Long.class, trainingName);

        String sqlCounter = "INSERT INTO CUSTOM_TRAININGS_COUNTER (username, trainingId, count) VALUES (?, ?, 0)";
        jdbc.update(sqlCounter, username, newTrainingId);

        for (Composition e : exercises) {
            String sqlExercises = "INSERT INTO EXERCISES (exercise_name, sets, reps, training_id) VALUES (?, ?, ?, ?)";
            jdbc.update(sqlExercises, e.getExerciseName(), e.getSets(), e.getReps(), newTrainingId);
        }

        return true;
    }

    public List<Map<String, Object>> getAdminStatistics() {
        String sql = """
            SELECT
                a.authority,
                AVG(u.count_training0) AS full_body,
                AVG(u.count_training1) AS push_pull_legs,
                AVG(u.count_training2) AS cardio,
                AVG(u.count_training3) AS strength
            FROM USERDATA u
            JOIN AUTHORITIES a ON u.username = a.username
            WHERE a.authority IN ('ROLE_USER_BASIC', 'ROLE_USER_PRO')
            GROUP BY a.authority
            ORDER BY a.authority
            """;
        return jdbc.queryForList(sql);
    }
}
