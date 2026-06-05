package exam_project.main_webapp.repositories;

import exam_project.main_webapp.mappers.EsercizioMapper;
import exam_project.main_webapp.mappers.ProgrammaMapper;
import exam_project.main_webapp.proxies.ProgrammiProxy;
import exam_project.main_webapp.pojos.Composizione;
import exam_project.main_webapp.pojos.ComposizioneCustom;
import exam_project.main_webapp.pojos.Programma;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Objects;

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
    public boolean addAllenamento(String username, String pC, List<Composizione> esercizi){

        List<Programma> ProgrammiCustom = this.getProgrammiCustom(username);
        for(Programma P : ProgrammiCustom){
            if(Objects.equals(P.getNome(), pC)){ return false; }
        }

        String sqlProgrammi ="INSERT INTO PROGRAMMICUSTOM (nome,kcal,username) VALUES ( ?, ?, ?)";
        jdbc.update(sqlProgrammi,pC,programmiProxy.getKcal(esercizi),username);

        String sqlId = "SELECT MAX(id) FROM PROGRAMMICUSTOM WHERE nome = ?";
        Long idProgrammaNuovo = jdbc.queryForObject(sqlId, Long.class, pC);

        String sqlCounter = "INSERT INTO CUSTOM_TRAININGS_COUNTER (username, trainingId, count) VALUES ( ?, ?, 0)";
        jdbc.update(sqlCounter, username, idProgrammaNuovo);

        for (Composizione e: esercizi){
            String sqlEsercizi = "INSERT INTO ESERCIZI (nome_esercizio,numero_serie,numero_ripetizioni,programmaType) VALUES (? ,? ,? ,?) ";
            jdbc.update(sqlEsercizi,e.getNome_esercizio(),e.getNumero_serie(),e.getNumero_ripetizioni(),idProgrammaNuovo);

        }

        return true;
    }

    public List<Programma> getProgrammiCustom(String username){
        String sql = "SELECT * FROM PROGRAMMICUSTOM WHERE username = ?";
        return jdbc.query(sql,new ProgrammaMapper(),username);
    }

    public List<ComposizioneCustom> getEserciziCustom(int id){
        String sql = "SELECT * FROM ESERCIZI WHERE programmaType = ? ";
        return jdbc.query(sql,new EsercizioMapper(),id);
    }

    public List<Map<String, Object>> getAdminStatistics() {
        String sql = """
            SELECT
                a.authority,
                AVG(u.count_training0) AS full_body,
                AVG(u.count_training1) AS push_pull_legs,
                AVG(u.count_training2) AS cardio,
                AVG(u.count_training3) AS strength
            FROM USERDATA u
            JOIN AUTHORITIES a ON u.username = a.username
            WHERE a.authority IN ('ROLE_USER_BASIC', 'ROLE_USER_PRO')
            GROUP BY a.authority
            ORDER BY a.authority
            """;
        return jdbc.queryForList(sql);
    }
}
