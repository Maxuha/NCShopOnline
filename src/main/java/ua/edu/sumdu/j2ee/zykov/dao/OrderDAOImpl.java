package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.exception.OrderNotExistException;
import ua.edu.sumdu.j2ee.zykov.mapper.OrderMapper;
import ua.edu.sumdu.j2ee.zykov.model.EntityField;
import ua.edu.sumdu.j2ee.zykov.model.Order;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Repository
public class OrderDAOImpl implements OrderDAO {
    private final JdbcTemplate jdbcTemplate;
    public OrderDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> findAll() {
        String query = "SELECT * FROM \"order\" o LEFT JOIN customer c on o.user_id = c.user_id LEFT JOIN \"user\" u on o.user_id = u.id;";
        return jdbcTemplate.query(query, new OrderMapper());
    }

    @Override
    public Order findById(int id) throws OrderNotExistException {
        String query = "SELECT * FROM \"order\" o LEFT JOIN customer c on o.user_id = c.user_id LEFT JOIN \"user\" u on o.user_id = u.id WHERE o.id = ?;";
        final String field = "id";
        try {
            return jdbcTemplate.queryForObject(query, new OrderMapper(), id);
        } catch (DataAccessException e) {
            throw new OrderNotExistException(new EntityField(id, field), e);
        }
    }

    @Override
    public Order findByProcessedAndCustomerId(int customerId) throws OrderNotExistException {
        String query = "SELECT * FROM \"order\" o LEFT JOIN customer c on o.user_id = c.user_id LEFT JOIN \"user\" u on o.user_id = u.id WHERE o.is_processed = ? AND o.user_id = ?;";
        final String field = "customer id";
        try {
            return jdbcTemplate.queryForObject(query, new OrderMapper(), false, customerId);
        } catch (DataAccessException e) {
            throw new OrderNotExistException(new EntityField(customerId, field), e);
        }
    }

    @Override
    public Order save(Order order) {
        String query = "INSERT INTO \"order\" (is_processed, \"date\", user_id) VALUES (?, ?, ?);";
        GeneratedKeyHolder holder = new GeneratedKeyHolder();
        jdbcTemplate.update(con -> {
            PreparedStatement statement = null;
            try {
                statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                statement.setBoolean(1, order.isProcessed());
                statement.setTimestamp(2, Timestamp.valueOf(order.getDate()));
                statement.setInt(3, order.getCustomer().getUser().getId());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return statement;
        }, holder);
        int primaryKey = (int) Objects.requireNonNull(holder.getKeys()).get("id");
        order.setId(primaryKey);
        return order;
    }

    @Override
    public Order update(Order order) {
        String query = "UPDATE \"order\" SET is_processed = ?, \"date\" = ?, user_id = ? WHERE id = ?;";
        jdbcTemplate.update(query, order.isProcessed(), Timestamp.valueOf(order.getDate()), order.getCustomer().getUser().getId(), order.getId());
        return order;
    }

    @Override
    public Order delete(Order order) {
        String query = "DELETE FROM \"order\" WHERE id = ?";
        jdbcTemplate.update(query, order.getId());
        return order;
    }
}
