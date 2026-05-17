package org.example.restlike.Pojos;

public class Esercizio{
    private int id;
    private String nome;
    private double unit;

    public double getUnit() {
        return unit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUnit(double unit) {
        this.unit = unit;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome_esercizio) {
        this.nome = nome_esercizio;
    }
}
