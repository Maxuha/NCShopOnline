package ua.edu.sumdu.j2ee.zykov.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.zykov.model.Customer;
import ua.edu.sumdu.j2ee.zykov.model.Order;
import ua.edu.sumdu.j2ee.zykov.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

public class OrderMapper implements RowMapper<Order> {
    @Override
    public Order mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("u.id"));
        user.setUserName(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        Customer customer = new Customer();
        customer.setUser(user);
        customer.setFullName(resultSet.getString("full_name"));
        Order order = new Order();
        order.setId(resultSet.getInt("o.id"));
        order.setProcessed(resultSet.getBoolean("is_processed"));
        order.setDate(resultSet.getDate("date").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        order.setCustomer(customer);
        return order;
    }
}
