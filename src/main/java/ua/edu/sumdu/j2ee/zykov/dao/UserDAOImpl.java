package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.mapper.UserMapper;
import ua.edu.sumdu.j2ee.zykov.model.User;

import java.sql.Statement;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO {
    private final JdbcTemplate jdbcTemplate;

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM user";
        return jdbcTemplate.query(query, new UserMapper());
    }

    @Override
    public User findById(int id) {
        String query = "SELECT * FROM user WHERE id = ?";
        return (User) jdbcTemplate.queryForObject(query, new UserMapper(), id);
    }

    @Override
    public User findByUserName(String username) {
        String query = "SELECT * FROM user WHERE username = ?";
        return (User) jdbcTemplate.queryForObject(query, new UserMapper(), username);
    }

    @Override
    public User save(User user) {
        String query = "INSERT INTO user (username, password) VALUES(?, ?)";
        jdbcTemplate.update(query, user.getUserName(), user.getPassword());
        return user;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE user SET username = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(query, user.getUserName(), user.getPassword(), user.getId());
        return user;
    }

    @Override
    public User delete(User user) {
        String query = "DELETE FROM user WHERE id = ?";
        jdbcTemplate.update(query, user.getId());
        return user;
    }
}
