package org.example.restlike.Mappers;

import org.example.restlike.Pojos.Esercizio;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EsercizioRowMapper implements RowMapper<Esercizio> {
    public Esercizio mapRow(ResultSet r, int i) throws SQLException {
        Esercizio es = new Esercizio();
        es.setId(r.getInt("id"));
        es.setNome(r.getString("nome"));
        es.setUnit(r.getDouble("unit"));
        return es;
    }
}
