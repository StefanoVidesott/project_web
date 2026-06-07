package exam_project.main_webapp.mappers;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

public class DefaultTrainingCountersMapper implements RowMapper<Map<String, Integer>> {
    @Override
    public Map<String, Integer> mapRow(ResultSet r, int i) throws SQLException {
        Map<String, Integer> counters = new LinkedHashMap<>();
        counters.put("Full Body",      r.getInt("count_training0"));
        counters.put("Push/Pull/Legs", r.getInt("count_training1"));
        counters.put("Cardio",         r.getInt("count_training2"));
        counters.put("Strength",       r.getInt("count_training3"));
        return counters;
    }
}
