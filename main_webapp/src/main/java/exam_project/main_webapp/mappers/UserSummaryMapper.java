package exam_project.main_webapp.mappers;

import exam_project.main_webapp.pojos.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserSummaryMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet r, int i) throws SQLException {
        User user = new User();
        user.setUsername(r.getString("username"));
        user.setFirstName(r.getString("firstName"));
        user.setLastName(r.getString("lastName"));
        user.setEmail(r.getString("email"));
        user.setSignupDate(r.getDate("signupDate"));
        user.setAuthority(r.getString("authority"));
        return user;
    }
}
