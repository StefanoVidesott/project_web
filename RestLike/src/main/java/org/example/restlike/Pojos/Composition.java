package org.example.restlike.Pojos;

public class Composition {
    private int id;
    private int sets;
    private int reps;
    private String trainingName;
    private String exerciseName;

    public int getId() { return id; }
    public int getSets() { return sets; }
    public int getReps() { return reps; }
    public String getTrainingName() { return trainingName; }
    public String getExerciseName() { return exerciseName; }

    public void setId(int id) { this.id = id; }
    public void setSets(int sets) { this.sets = sets; }
    public void setReps(int reps) { this.reps = reps; }
    public void setTrainingName(String trainingName) { this.trainingName = trainingName; }
    public void setExerciseName(String exerciseName) { this.exerciseName = exerciseName; }
}
