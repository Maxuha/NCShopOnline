package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.mapper.CustomerMapper;
import ua.edu.sumdu.j2ee.zykov.model.Customer;

import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {
    private final JdbcTemplate jdbcTemplate;

    public CustomerDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM customer LEFT JOIN \"user\" ON customer.user_id = \"user\".id;";
        return jdbcTemplate.query(sql, new CustomerMapper());
    }

    @Override
    public Customer findById(int id) {
        String sql = "SELECT * FROM customer LEFT JOIN \"user\" ON customer.user_id = \"user\".id WHERE user_id = ?;";
        return jdbcTemplate.queryForObject(sql, new CustomerMapper(), id);
    }

    @Override
    public Customer save(Customer customer) {
        String sql = "INSERT INTO customer (user_id, full_name) VALUES  (?, ?);";
        jdbcTemplate.update(sql, customer.getUser().getId(), customer.getFullName());
        return customer;
    }

    @Override
    public Customer update(Customer customer) {
        String sql = "UPDATE customer SET full_name = ? WHERE user_id = ;";
        jdbcTemplate.update(sql, customer.getFullName(), customer.getUser().getId());
        return customer;
    }

    @Override
    public Customer delete(Customer customer) {
        String sql = "DELETE FROM customer WHERE user_id = ?;";
        jdbcTemplate.update(sql, customer.getUser().getId());
        return customer;
    }
}
