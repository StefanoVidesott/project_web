package org.example.restlike.Repository;

import org.example.restlike.Mappers.CompositionRowMapper;
import org.example.restlike.Mappers.ExerciseRowMapper;
import org.example.restlike.Mappers.TrainingRowMapper;
import org.example.restlike.Pojos.Composition;
import org.example.restlike.Pojos.Exercise;
import org.example.restlike.Pojos.Training;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TrainingRepository {

    private final JdbcTemplate jdbcTemplate;

    public TrainingRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Training> getDefaultTrainings() {
        String sql = "SELECT * FROM TRAININGS";
        return jdbcTemplate.query(sql, new TrainingRowMapper());
    }

    public List<Composition> getExercisesByTrainingName(String name) {
        String sql = "SELECT * FROM COMPOSITIONS WHERE training_name = ?";
        return jdbcTemplate.query(sql, new CompositionRowMapper(), name);
    }

    public double getKcal(List<Composition> compositions) {
        double totalKcal = 0;
        for (Composition c : compositions) {
            String sql = "SELECT * FROM EXERCISES WHERE name = ?";
            Exercise exercise = jdbcTemplate.queryForObject(sql, new ExerciseRowMapper(), c.getExerciseName());
            totalKcal += c.getSets() * (c.getReps() * exercise.getUnit());
        }
        return totalKcal;
    }

    public List<String> getExerciseNames() {
        String sql = "SELECT name FROM EXERCISES";
        return jdbcTemplate.queryForList(sql, String.class);
    }
}
