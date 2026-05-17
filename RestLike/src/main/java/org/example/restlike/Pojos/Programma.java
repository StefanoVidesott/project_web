package org.example.restlike.Pojos;

public class Programma {
    private int id;

    private String nome;
    private String code;

    // Get e Set
    public void setId(int id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getId() { return this.id; }

    public String getNome() { return this.nome; }

    public String getCode() { return this.code; }

}
