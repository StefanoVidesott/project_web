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
import java.util.List;

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
        String sql = "SELECT u.*, a.authority FROM USERDATA u JOIN AUTHORITIES a ON u.username = a.username WHERE u.username = ?";
        RowMapper<User> userRowMapper = (r, i) -> {
            User rowObject = new User();
            rowObject.setId(r.getInt("id"));
            rowObject.setUsername(r.getString("username"));
            rowObject.setFirstName(r.getString("firstName"));
            rowObject.setLastName(r.getString("lastName"));
            rowObject.setEmail(r.getString("email"));
            rowObject.setBirthDate(r.getDate("birthDate"));
            rowObject.setSignupDate(r.getDate("signupDate"));
            rowObject.setAuthority(r.getString("authority"));
            return rowObject;
        };
        return jdbc.queryForObject(sql, userRowMapper, username);
    }

    public List<User> findAllUsers() {
        String sql = "SELECT u.*, a.authority FROM USERDATA u JOIN AUTHORITIES a ON u.username = a.username ORDER BY a.authority";
        RowMapper<User> userMapper = (rs, rowNum) -> {
            User ui = new User();
            ui.setUsername(rs.getString("username"));
            ui.setFirstName(rs.getString("firstName"));
            ui.setLastName(rs.getString("lastName"));
            ui.setEmail(rs.getString("email"));
            ui.setSignupDate(rs.getDate("signupDate"));
            ui.setAuthority(rs.getString("authority"));
            return ui;
        };
        return jdbc.query(sql, userMapper);
    }

    @Transactional
    public int removeDisabledProvaUsers() {
        String sqlFind = "SELECT u.username FROM USERS u JOIN AUTHORITIES a ON u.username = a.username WHERE u.enabled = 0 AND a.authority = 'ROLE_USER_PROVA'";
        List<String> usersToRemove = jdbc.queryForList(sqlFind, String.class);

        if (usersToRemove.isEmpty()) return 0;

        for (String username : usersToRemove) {
            jdbc.update("DELETE FROM AUTHORITIES WHERE username = ?", username);
            jdbc.update("DELETE FROM USERDATA WHERE username = ?", username);
            jdbc.update("DELETE FROM USERS WHERE username = ?", username);
        }

        return usersToRemove.size();
    }

    public boolean changePassword(String username, String oldPassword, String newPassword) {
        String sql = "SELECT password FROM USERS WHERE username = ?";
        String currentHashedPassword = jdbc.queryForObject(sql, String.class, username);

        if (!passwordEncoder.matches(oldPassword, currentHashedPassword)) {
            return false;
        }

        String updateSql = "UPDATE USERS SET password = ? WHERE username = ?";
        jdbc.update(updateSql, passwordEncoder.encode(newPassword), username);
        return true;
    }

    public void upgradeUser(String username, String newAuthority) {
        String sql = "UPDATE AUTHORITIES SET authority = ? WHERE username = ?";
        jdbc.update(sql, newAuthority, username);
    }

}
