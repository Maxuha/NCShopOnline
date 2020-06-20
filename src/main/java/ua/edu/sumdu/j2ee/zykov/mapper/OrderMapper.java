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
        user.setId(resultSet.getInt(7));
        user.setUserName(resultSet.getString(8));
        user.setPassword(resultSet.getString(9));
        Customer customer = new Customer();
        customer.setUser(user);
        customer.setFullName(resultSet.getString(6));
        Order order = new Order();
        order.setId(resultSet.getInt(1));
        order.setProcessed(resultSet.getBoolean(2));
        order.setDate(resultSet.getTimestamp(3).toLocalDateTime());
        order.setCustomer(customer);
        return order;
    }
}
