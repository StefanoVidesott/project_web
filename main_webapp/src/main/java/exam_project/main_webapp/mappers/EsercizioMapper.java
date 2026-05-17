package exam_project.main_webapp.mappers;

import exam_project.main_webapp.pojos.Composizione;
import exam_project.main_webapp.pojos.ComposizioneCustom;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EsercizioMapper implements RowMapper<ComposizioneCustom> {
    public ComposizioneCustom mapRow(ResultSet r,int i) throws SQLException{
        ComposizioneCustom c = new ComposizioneCustom();
        c.setId(r.getInt("id"));
        c.setNome_esercizio(r.getString("nome_esercizio"));
        c.setNumero_ripetizioni(r.getInt("numero_ripetizioni"));
        c.setNumero_serie(r.getInt("numero_serie"));
        c.setProgrammaType(r.getInt("programmaType"));
        return c;
    }
}
