package exam_project.main_webapp.mappers;

import exam_project.main_webapp.pojos.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet r, int i) throws SQLException {
        User user = new User();
        user.setId(r.getInt("id"));
        user.setUsername(r.getString("username"));
        user.setFirstName(r.getString("firstName"));
        user.setLastName(r.getString("lastName"));
        user.setEmail(r.getString("email"));
        user.setBirthDate(r.getDate("birthDate"));
        user.setSignupDate(r.getDate("signupDate"));
        user.setAuthority(r.getString("authority"));
        user.setCountTraining0(r.getInt("count_training0"));
        user.setCountTraining1(r.getInt("count_training1"));
        user.setCountTraining2(r.getInt("count_training2"));
        user.setCountTraining3(r.getInt("count_training3"));
        return user;
    }
}
