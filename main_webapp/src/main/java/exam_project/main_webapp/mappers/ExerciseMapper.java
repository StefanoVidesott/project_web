package exam_project.main_webapp.mappers;

import exam_project.main_webapp.pojos.CustomComposition;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseMapper implements RowMapper<CustomComposition> {
    public CustomComposition mapRow(ResultSet r, int i) throws SQLException {
        CustomComposition c = new CustomComposition();
        c.setId(r.getInt("id"));
        c.setExerciseName(r.getString("exercise_name"));
        c.setReps(r.getInt("reps"));
        c.setSets(r.getInt("sets"));
        c.setTrainingId(r.getInt("training_id"));
        return c;
    }
}
