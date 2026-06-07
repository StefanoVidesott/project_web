package org.example.restlike.Mappers;

import org.example.restlike.Pojos.Training;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainingRowMapper implements RowMapper<Training> {
    public Training mapRow(ResultSet r, int i) throws SQLException {
        Training t = new Training();
        t.setId(r.getInt("id"));
        t.setName(r.getString("name"));
        return t;
    }
}
