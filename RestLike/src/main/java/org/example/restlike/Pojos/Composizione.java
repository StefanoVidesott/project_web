package org.example.restlike.Pojos;

public class Composizione{
    private int id;
    private String nome_esercizio;
    private int numero_serie;
    private int numero_ripetizioni;
    private String esercizioType;
    private String programmaType;

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

    public String getEsercizioType() {
        return esercizioType;
    }

    public void setEsercizioType(String esercizioType) {
        this.esercizioType = esercizioType;
    }

    public int getNumero_ripetizioni() { return this.numero_ripetizioni; }

    public String getProgrammaType() { return this.programmaType; }

}
