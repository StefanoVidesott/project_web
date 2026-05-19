package exam_project.main_webapp.repositories;

import exam_project.main_webapp.pojos.Training;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public class TrainingRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    public TrainingRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public void registerTraining(String username, int trainingID) {
        String sql = "INSERT INTO COMPLETED_TRAININGS (username, trainingID, trainingTimestamp) VALUES (?, ?, ?)";
        jdbc.update(sql, username, trainingID, Timestamp.valueOf(LocalDateTime.now()));
    }

    public int countTrainingsByUsername(String username) {
        String sql = "SELECT COUNT(*) FROM COMPLETED_TRAININGS WHERE username = ?";
        Integer count = jdbc.queryForObject(sql, Integer.class, username);
        return count != null ? count : 0;
    }

    public List<Map<String, Object>> getStatisticsGroupedByTraining() {
        String sql = """
                SELECT a.trainingID, COUNT(*) * 1.0 / COUNT(DISTINCT a.username) AS AVERAGE, auth.authority
                FROM COMPLETED_TRAININGS a
                JOIN AUTHORITIES auth ON a.username = auth.username
                WHERE auth.authority IN ('ROLE_USER_BASIC', 'ROLE_USER_PRO')
                GROUP BY a.trainingID, auth.authority
                ORDER BY auth.authority, a.trainingID
                """;
        return jdbc.queryForList(sql);
    }

    public List<Map<String, Object>> getStatisticsByUsername(String username) {
        String sql = """
                SELECT trainingID, COUNT(*) AS count
                FROM ALLENAMENTI_COMPLETATI
                WHERE username = ?
                GROUP BY trainingID
                ORDER BY trainingID
                """;
        return jdbc.queryForList(sql, username);
    }
}