package org.example.restlike.Mappers;

import org.example.restlike.Pojos.Composizione;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ComposizioneRowMapper implements RowMapper<Composizione> {

    public Composizione mapRow(ResultSet r,int i) throws SQLException{
        Composizione comp = new Composizione();
        comp.setNome_esercizio(r.getString("nome_esercizio"));
        comp.setNumero_serie(r.getInt("numero_serie"));
        comp.setNumero_ripetizioni(r.getInt("numero_ripetizioni"));
        comp.setProgrammaType(r.getString("programmaType"));
        comp.setEsercizioType(r.getString("esercizioType"));
        return comp;
    }
}
