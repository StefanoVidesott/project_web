package exam_project.main_webapp.pojos;

public class Composizione {
    private int id;
    private String nome_esercizio;
    private int numero_serie;
    private int numero_ripetizioni;
    private String programmaType;

    // Get e Set

    public void setId(int id) {
        this.id = id;
    }

    public void setNome_esercizio(String nome) {
        this.nome_esercizio = nome;
    }

    public void setNumero_serie(int serie) {
        this.numero_serie = serie;
    }

    public void setNumero_ripetizioni(int ripetizioni) {
        this.numero_ripetizioni = ripetizioni;
    }

    public void setProgrammaType(String programmaType) {
        this.programmaType = programmaType;
    }

    public int getId() { return this.id; }

    public String getNome_esercizio() {
        return this.nome_esercizio;
    }

    public int getNumero_serie() { return this.numero_serie; }

    public int getNumero_ripetizioni() { return this.numero_ripetizioni; }

    public String getProgrammaType() { return this.programmaType; }

}
