package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.mapper.OrderMapper;
import ua.edu.sumdu.j2ee.zykov.model.Order;

import java.util.List;

@Repository
public class OrderDAOImpl implements OrderDAO {
    private final JdbcTemplate jdbcTemplate;

    public OrderDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> findAll() {
        String sql = "SELECT * FROM \"order\" o LEFT JOIN customer c on o.user_id = c.user_id LEFT JOIN \"user\" u on o.user_id = u.id;";
        return jdbcTemplate.query(sql, new OrderMapper());
    }

    @Override
    public Order findById(int id) {
        String sql = "SELECT * FROM \"order\" o LEFT JOIN customer c on o.user_id = c.user_id LEFT JOIN \"user\" u on o.user_id = u.id WHERE id = ?;";
        return jdbcTemplate.queryForObject(sql, new OrderMapper(), id);
    }

    @Override
    public Order save(Order order) {
        String sql = "INSERT INTO order (id, is_processed, date, user_id) VALUES (?, ?, ?, ?);";
        jdbcTemplate.update(sql, order.getId(), order.isProcessed(), order.getDate(), order.getCustomer().getUser().getId());
        return order;
    }

    @Override
    public Order update(Order order) {
        String sql = "UPDATE order SET is_processed = ?, date = ?, user_id = ? WHERE id = ?;";
        jdbcTemplate.update(sql, order.isProcessed(), order.getDate(), order.getCustomer().getUser().getId(), order.getId());
        return order;
    }

    @Override
    public Order delete(Order order) {
        String sql = "DELETE FROM order WHERE id = ?";
        jdbcTemplate.update(sql, order.getId());
        return order;
    }
}
