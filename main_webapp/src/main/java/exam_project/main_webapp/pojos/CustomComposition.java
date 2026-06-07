package exam_project.main_webapp.pojos;

public class CustomComposition {
    private int id;
    private int sets;
    private int reps;
    private int trainingId;
    private String exerciseName;

    public int getId() { return id; }
    public String getExerciseName() { return exerciseName; }
    public int getSets() { return sets; }
    public int getReps() { return reps; }
    public int getTrainingId() { return trainingId; }

    public void setTrainingId(int trainingId) { this.trainingId = trainingId; }
    public void setId(int id) { this.id = id; }
    public void setExerciseName(String exerciseName) { this.exerciseName = exerciseName; }
    public void setSets(int sets) { this.sets = sets; }
    public void setReps(int reps) { this.reps = reps; }
}
