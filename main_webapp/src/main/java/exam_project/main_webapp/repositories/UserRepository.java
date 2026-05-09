package exam_project.main_webapp.repositories;

import exam_project.main_webapp.pojos.SecurityUser;
import exam_project.main_webapp.pojos.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbc;
    private final UserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserRepository(JdbcTemplate jdbc,
                          UserDetailsManager userDetailsManager,
                          PasswordEncoder passwordEncoder) {
        this.jdbc = jdbc;
        this.userDetailsManager = userDetailsManager;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean userExists(String username) {
        return userDetailsManager.userExists(username);
    }

    @Transactional
    public void addUser(User user) {
        String sql = "INSERT INTO USERDATA (username, firstName, lastName, email, birthDate, signupDate) VALUES (?, ?, ?, ?, ?, ?)";
        jdbc.update(sql,
                user.getUsername(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getBirthDate(),
                Date.valueOf(LocalDate.now())
        );

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDetailsManager.createUser(new SecurityUser(user));
    }

    public User findUserByUsername(String username) {
        String sql = "SELECT * FROM USERDATA WHERE username = ?";
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setUsername(r.getString("username"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            rowObject.setEmail(r.getString("email"));
            rowObject.setBirthDate(r.getDate("birthDate"));
            rowObject.setSignupDate(r.getDate("signupDate"));
            return rowObject;
        };
        return jdbc.queryForObject(sql, userRowMapper, username);
    }

}
