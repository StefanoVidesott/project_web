package exam_project.main_webapp.pojos;

public class Composition {
    private int id;
    private int sets;
    private int reps;
    private String trainingName;
    private String exerciseName;

    public void setId(int id) { this.id = id; }
    public void setSets(int sets) { this.sets = sets; }
    public void setReps(int reps) { this.reps = reps; }
    public void setTrainingName(String trainingName) { this.trainingName = trainingName; }
    public void setExerciseName(String exerciseName) { this.exerciseName = exerciseName; }

    public int getId() { return this.id; }
    public int getSets() { return this.sets; }
    public int getReps() { return this.reps; }
    public String getTrainingName() { return this.trainingName; }
    public String getExerciseName() { return this.exerciseName; }
}
