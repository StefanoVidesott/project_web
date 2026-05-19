package exam_project.main_webapp.mappers;

import exam_project.main_webapp.pojos.Programma;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgrammaMapper implements RowMapper<Programma>{

    public Programma mapRow(ResultSet r,int i) throws SQLException{
        Programma p = new Programma();
        p.setId(r.getInt("id"));
        p.setNome(r.getString("nome"));
        p.setKcal(r.getDouble("kcal"));
        return p;
    }
}
