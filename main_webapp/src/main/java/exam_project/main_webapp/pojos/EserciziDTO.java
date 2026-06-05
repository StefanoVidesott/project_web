package exam_project.main_webapp.pojos;

import java.util.ArrayList;
import java.util.List;

public class EserciziDTO {
   // private String nomeProgramma;
    private List<Composizione> esercizi = new ArrayList<>();


  //  public String getNomeProgramma() { return nomeProgramma; }
  //  public void setNomeProgramma(String nome) { this.nomeProgramma = nome; }
  public List<Composizione> getEsercizi() { return esercizi; }
  //  public void setEsercizi(List<Esercizio> esercizi) { this.esercizi = esercizi; }
}
