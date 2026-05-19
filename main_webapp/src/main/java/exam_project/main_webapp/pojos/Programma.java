package exam_project.main_webapp.pojos;


public class Programma {
    private int id;
    private int userId;
    private String nome;
    private double kcal;


    // Get e Set

    public void setId(int id) { this.id = id; }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setKcal(double kcal) {
        this.kcal = kcal;
    }

    public int getId() { return this.id; }

    public String getNome() { return this.nome; }

    public double getKcal() {
        return this.kcal;
    }
}