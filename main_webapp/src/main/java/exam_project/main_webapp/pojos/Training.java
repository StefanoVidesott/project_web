package exam_project.main_webapp.pojos;

import java.sql.Timestamp;

public class Training {
    private int id;
    private String username;
    private int trainingId;
    private Timestamp trainingTimestamp;

    public Training() {
        this.username  = "";
        this.trainingId = 0;
        this.trainingTimestamp = null;
    }

    public Training(String username, int programma, Timestamp trainingTimestamp) {
        this.username  = username;
        this.trainingId = programma;
        this.trainingTimestamp = trainingTimestamp;
    }

    public int getId()             { return id; }
    public String getUsername()    { return username; }
    public int getTrainingId()   { return trainingId; }
    public Timestamp getTrainingTimestamp()  { return trainingTimestamp; }

    public void setId(int id)                  { this.id = id; }
    public void setUsername(String username)   { this.username = username; }
    public void setTrainingId(int trainingId) { this.trainingId = trainingId; }
    public void setTrainingTimestamp(Timestamp trainingTimestamp)  { this.trainingTimestamp = trainingTimestamp; }
}