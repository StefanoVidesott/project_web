package exam_project.main_webapp.pojos;

import java.sql.Date;

public class User {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String username;
    private String password;
    private String authority;
    private Date birthDate;
    private Date signupDate;

    public User() {
        this.id = 0;
        this.firstName  = "";
        this.lastName   = "";
        this.email      = "";
        this.username   = "";
        this.password   = "";
        this.authority  = "";
        this.birthDate  = null;
        this.signupDate = null;
    }

    public User(String firstName, String lastName, String email,
                String username, String password, String authority,
                Date birthDate, Date signupDate) {
        this.firstName  = firstName;
        this.lastName   = lastName;
        this.email      = email;
        this.username   = username;
        this.password   = password;
        this.authority  = authority;
        this.birthDate  = birthDate;
        this.signupDate = signupDate;
    }

    // Getters and Setters
    public int getId()               { return id; }
    public String getFirstName()     { return firstName; }
    public String getLastName()      { return lastName; }
    public String getEmail()         { return email; }
    public String getUsername()      { return username; }
    public String getPassword()      { return password; }
    public String getAuthority()     { return authority; }
    public Date getBirthDate()       { return birthDate; }
    public Date getSignupDate()      { return signupDate; }

    public void setId(int id)                    { this.id = id; }
    public void setFirstName(String firstName)   { this.firstName = firstName; }
    public void setLastName(String lastName)     { this.lastName = lastName; }
    public void setEmail(String email)           { this.email = email; }
    public void setUsername(String username)     { this.username = username; }
    public void setPassword(String password)     { this.password = password; }
    public void setAuthority(String authority)   { this.authority = authority; }
    public void setBirthDate(Date birthDate)     { this.birthDate = birthDate; }
    public void setSignupDate(Date signupDate)   { this.signupDate = signupDate; }
}
