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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
        String sql = "INSERT INTO USERDATA (username, firstName, lastName, email, birthDate, signupDate, count_training0, count_training1, count_training2, count_training3) VALUES (?, ?, ?, ?, ?, ?, 0, 0, 0, 0)";
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
            rowObject.setCountTraining0(r.getInt("count_training0"));
            rowObject.setCountTraining1(r.getInt("count_training1"));
            rowObject.setCountTraining2(r.getInt("count_training2"));
            rowObject.setCountTraining3(r.getInt("count_training3"));
            return rowObject;
        };
        return jdbc.queryForObject(sql, userRowMapper, username);
    }

    public List<User> findAllUsers() {
        String sql = "SELECT u.*, a.authority FROM USERDATA u JOIN AUTHORITIES a ON u.username = a.username ORDER BY a.authority, u.signupDate";
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
    public int deleteDisabledProvaUsers() {
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

    public void incrementTrainingCounter(String username, int trainingId, boolean isDefault) {
        if (isDefault) {
            String col = "count_training" + trainingId;
            String sql = "UPDATE USERDATA SET " + col + " = " + col + " + 1 WHERE username = ?";
            jdbc.update(sql, username);
        }
        else {
            String sql = "UPDATE CUSTOM_TRAININGS_COUNTER SET count  = count + 1 WHERE username = ? AND trainingID = ?";
            jdbc.update(sql, username, trainingId);
        }
    }

    public int getDefaultTrainingsCount(String username) {
        String sql = """
            SELECT count_training0 + count_training1 + count_training2 + count_training3
            FROM USERDATA WHERE username = ?
            """;
        Integer total = jdbc.queryForObject(sql, Integer.class, username);
        return total != null ? total : 0;
    }

    public Map<String, Integer> getTrainingsCountersByUsername(String username) {
        Map<String, Integer> result = new LinkedHashMap<>();

        // Default programs
        String sqlDefaultCounters = """
            SELECT count_training0, count_training1, count_training2, count_training3
            FROM USERDATA WHERE username = ?
            """;
        Map<String, Integer> defaultCounters = jdbc.queryForObject(sqlDefaultCounters, (rs, rowNum) -> {
            Map<String, Integer> m = new LinkedHashMap<>();
            m.put("Full Body",      rs.getInt("count_training0"));
            m.put("Push/Pull/Legs", rs.getInt("count_training1"));
            m.put("Cardio",         rs.getInt("count_training2"));
            m.put("Strength",       rs.getInt("count_training3"));
            return m;
        }, username);

        if (defaultCounters != null) {
            result.putAll(defaultCounters);
        }

        // Custom programs
        String sqlCustomCounters = """
            SELECT pc.nome, cts.count
            FROM CUSTOM_TRAININGS_COUNTER cts
            JOIN PROGRAMMICUSTOM pc ON cts.trainingId = pc.id
            WHERE cts.username = ?
            """;
        jdbc.query(sqlCustomCounters, (rs) -> {
            result.put(rs.getString("nome"), rs.getInt("count"));
        }, username);

        return result;
    }

    public void disableUser(String username) {
        String sql = "UPDATE USERS SET enabled = 0 WHERE username = ?";
        jdbc.update(sql, username);
    }
}
