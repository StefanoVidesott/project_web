package exam_project.main_webapp.mappers;

import exam_project.main_webapp.pojos.Training;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TrainingMapper implements RowMapper<Training> {
    @Override
    public Training mapRow(ResultSet r, int i) throws SQLException {
        Training t = new Training();
        t.setId(r.getInt("id"));
        t.setName(r.getString("name"));
        t.setKcal(r.getDouble("kcal"));
        return t;
    }
}
