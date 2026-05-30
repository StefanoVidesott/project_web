package exam_project.main_webapp.repositories;

import exam_project.main_webapp.pojos.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class ReviewRepository {
    private final JdbcTemplate jdbc;

    @Autowired
    public ReviewRepository(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Review> findAll() {
        String sql = "SELECT * FROM REVIEWS";
        RowMapper<Review> mapper = (rs, rowNum) -> {
            Review r = new Review();
            r.setId(rs.getInt("id"));
            r.setUsername(rs.getString("username"));
            r.setContent(rs.getString("content"));
            return r;
        };
        return jdbc.query(sql, mapper);
    }

    public Review save(String username, String content) {
        String sql = "INSERT INTO REVIEWS (username, content) VALUES (?, ?)";
        jdbc.update(sql, username, content);

        // Last saved review to send to frontend
        String sqlLast = "SELECT * FROM REVIEWS WHERE username = ? ORDER BY id DESC LIMIT 1";
        RowMapper<Review> mapper = (rs, rowNum) -> {
            Review r = new Review();
            r.setId(rs.getInt("id"));
            r.setUsername(rs.getString("username"));
            r.setContent(rs.getString("content"));
            return r;
        };
        return jdbc.queryForObject(sqlLast, mapper, username);
    }
}
