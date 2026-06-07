package exam_project.main_webapp.pojos;

public class Training {
    private int id;
    private int userId;
    private String name;
    private double kcal;

    public void setId(int id) { this.id = id; }
    public void setUserId(int userId) { this.userId = userId; }
    public void setName(String name) { this.name = name; }
    public void setKcal(double kcal) { this.kcal = kcal; }

    public int getId() { return this.id; }
    public int getUserId() { return userId; }
    public String getName() { return this.name; }
    public double getKcal() { return this.kcal; }
}
