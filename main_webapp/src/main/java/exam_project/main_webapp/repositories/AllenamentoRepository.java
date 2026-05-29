package exam_project.main_webapp.repositories;

import exam_project.main_webapp.mappers.EsercizioMapper;
import exam_project.main_webapp.mappers.ProgrammaMapper;
import exam_project.main_webapp.Proxy.ProgrammiProxy;
import exam_project.main_webapp.pojos.Composizione;
import exam_project.main_webapp.pojos.ComposizioneCustom;
import exam_project.main_webapp.pojos.Programma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class AllenamentoRepository {
    private final JdbcTemplate jdbc;
    private final ProgrammiProxy programmiProxy;

    @Autowired
    public AllenamentoRepository(JdbcTemplate jdbc,ProgrammiProxy programmiProxy){
        this.jdbc = jdbc;
        this.programmiProxy = programmiProxy;
    }

    public void addKcalPredefinit(List<Programma> programmi){
        for(Programma p:programmi){
           p.setKcal(this.programmiProxy.getKcal(this.programmiProxy.getEsercizi(p.getNome())));
        }
    }

    @Transactional
    public void addAllenamento(String username, String pC, List<Composizione> esercizi){

        String sqlProgrammi ="INSERT INTO PROGRAMMICUSTOM (nome,kcal,username) VALUES ( ?, ?, ?)";
        jdbc.update(sqlProgrammi,pC,programmiProxy.getKcal(esercizi),username);

        String sqlId = "SELECT MAX(id) FROM PROGRAMMICUSTOM WHERE nome = ?";
        Long idProgrammaNuovo = jdbc.queryForObject(sqlId, Long.class, pC);

        for (Composizione e: esercizi){
            String sqlEsercizi = "INSERT INTO ESERCIZI (nome_esercizio,numero_serie,numero_ripetizioni,programmaType) VALUES (? ,? ,? ,?) ";
            jdbc.update(sqlEsercizi,e.getNome_esercizio(),e.getNumero_serie(),e.getNumero_ripetizioni(),idProgrammaNuovo);

        }

    }

    public List<Programma> getProgrammiCustom(String username){
        String sql = "SELECT * FROM PROGRAMMICUSTOM WHERE username = ?";
        return jdbc.query(sql,new ProgrammaMapper(),username);
    }

    public List<ComposizioneCustom> getEserciziCustom(int id){
        String sql = "SELECT * FROM ESERCIZI WHERE programmaType = ? ";
        return jdbc.query(sql,new EsercizioMapper(),id);
    }
}
