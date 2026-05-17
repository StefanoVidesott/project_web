package org.example.restlike.Repository;

import org.example.restlike.Mappers.ComposizioneRowMapper;
import org.example.restlike.Mappers.EsercizioRowMapper;
import org.example.restlike.Pojos.Composizione;
import org.example.restlike.Pojos.Esercizio;
import org.example.restlike.Pojos.Programma;
import org.example.restlike.Mappers.ProgrammaRowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

@Repository
public class ProgrammiRepository {

    private final JdbcTemplate jdbcTemplate;

    public ProgrammiRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    /* private List<KcalUnit> getKcalUnit(){
        String sql = "SELECT * FROM KCAL";
        return jdbcTemplate.query(sql,new KcalUnitRowMapper() );
    }*/

    public List<Programma> getProgrammi(){
        String sql = "SELECT * FROM Programmi";
        return jdbcTemplate.query(sql,new ProgrammaRowMapper());
    }

    public List<Composizione> getEsercizi(String code){
        String sql = "SELECT * FROM Composizioni WHERE programmaType = ? ";
        return jdbcTemplate.query(sql,new ComposizioneRowMapper(),code);
    }

    public double getKcal(List<Composizione> composizioni){

        double totKcal = 0;
        for (Composizione c: composizioni){

            String sql = "SELECT * FROM Esercizi WHERE nome = ?";
            Esercizio tmp = jdbcTemplate.queryForObject(sql,new EsercizioRowMapper(),c.getNome_esercizio());
            totKcal += (c.getNumero_serie() * (c.getNumero_ripetizioni() * tmp.getUnit()) );

        }

        return totKcal;
    }

}
