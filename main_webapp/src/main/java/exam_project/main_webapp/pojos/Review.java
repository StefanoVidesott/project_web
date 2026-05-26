package exam_project.main_webapp.pojos;

import java.sql.Timestamp;

public class Review {
    private int id;
    private String username;
    private String content;
    private Timestamp createdAt;

    public Review() {
        this.id = 0;
        this.username = "";
        this.content = "";
        this.createdAt = null;
    }

    public Review(int id, String username, String content, Timestamp createdAt) {
        this.id = id;
        this.username = username;
        this.content = content;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }
    public String getUsername() {
        return username;
    }
    public String getContent() {
        return content;
    }
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setContent(String content) {
        this.content = content;
    }
    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
