package org.example.restlike.Mappers;


import org.example.restlike.Pojos.Programma;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProgrammaRowMapper implements RowMapper<Programma>{

    public Programma mapRow(ResultSet r, int i) throws SQLException{
        Programma p = new Programma();
        p.setId(r.getInt("id"));
        p.setNome(r.getString("nome"));
        return p;
    }
}
