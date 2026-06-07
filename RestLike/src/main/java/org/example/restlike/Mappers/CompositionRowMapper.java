package org.example.restlike.Mappers;

import org.example.restlike.Pojos.Composition;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CompositionRowMapper implements RowMapper<Composition> {
    public Composition mapRow(ResultSet r, int i) throws SQLException {
        Composition c = new Composition();
        c.setSets(r.getInt("sets"));
        c.setReps(r.getInt("reps"));
        c.setTrainingName(r.getString("training_name"));
        c.setExerciseName(r.getString("exercise_name"));
        return c;
    }
}
