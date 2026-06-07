package org.example.restlike.Mappers;

import org.example.restlike.Pojos.Exercise;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ExerciseRowMapper implements RowMapper<Exercise> {
    public Exercise mapRow(ResultSet r, int i) throws SQLException {
        Exercise e = new Exercise();
        e.setId(r.getInt("id"));
        e.setName(r.getString("name"));
        e.setUnit(r.getDouble("unit"));
        return e;
    }
}
