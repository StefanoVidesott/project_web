package exam_project.main_webapp.pojos;

public class ComposizioneCustom {
    private int id;
    private String nome_esercizio;
    private int numero_serie;
    private int numero_ripetizioni;
    private int programmaType;

    public int getId() {
        return id;
    }

    public int getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(int numero_serie) {
        this.numero_serie = numero_serie;
    }

    public int getProgrammaType() {
        return programmaType;
    }

    public void setProgrammaType(int programmaType) {
        this.programmaType = programmaType;
    }

    public int getNumero_ripetizioni() {
        return numero_ripetizioni;
    }

    public void setNumero_ripetizioni(int numero_ripetizioni) {
        this.numero_ripetizioni = numero_ripetizioni;
    }

    public String getNome_esercizio() {
        return nome_esercizio;
    }

    public void setNome_esercizio(String nome_esercizio) {
        this.nome_esercizio = nome_esercizio;
    }

    public void setId(int id) {
        this.id = id;
    }
}
