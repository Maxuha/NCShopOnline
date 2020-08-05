package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.mapper.UserMapper;
import ua.edu.sumdu.j2ee.zykov.model.User;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Objects;

@Repository
public class UserDAOImpl implements UserDAO {
    private final JdbcTemplate jdbcTemplate;

    public UserDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<User> findAll() {
        String query = "SELECT * FROM \"user\"";
        return jdbcTemplate.query(query, new UserMapper());
    }

    @Override
    public User findById(int id) {
        String query = "SELECT * FROM \"user\" WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new UserMapper(), id);
    }

    @Override
    public User findByUserName(String username) {
        String query = "SELECT * FROM \"user\" WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(query, new UserMapper(), username);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public User save(User user) {
        String query = "INSERT INTO \"user\" (username, password) VALUES(?, ?)";
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement = null;
            try {
                statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, user.getUserName());
                statement.setString(2, user.getPassword());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return statement;
        }, holder);
        int primaryKey = (int) Objects.requireNonNull(holder.getKeys()).get("id");
        user.setId(primaryKey);
        return user;
    }

    @Override
    public User update(User user) {
        String query = "UPDATE \"user\" SET username = ?, password = ? WHERE id = ?";
        jdbcTemplate.update(query, user.getUserName(), user.getPassword(), user.getId());
        return user;
    }

    @Override
    public User delete(User user) {
        String query = "DELETE FROM \"user\" WHERE id = ?";
        jdbcTemplate.update(query, user.getId());
        return user;
    }
}
