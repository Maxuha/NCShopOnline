package ua.edu.sumdu.j2ee.zykov.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.zykov.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.ZoneId;

public class ProductHasOrderMapper implements RowMapper<ProductHasOrder> {
    @Override
    public ProductHasOrder mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("u.id"));
        user.setUserName(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        Image image = new Image();
        image.setId(resultSet.getInt("i.id"));
        image.setImage(resultSet.getString("image"));
        Category category = new Category();
        category.setId(resultSet.getInt("c.id"));
        category.setTitle(resultSet.getString("c.title"));
        category.setImage(image);
        category.setParent(null);
        Shipper shipper = new Shipper();
        shipper.setUser(user);
        shipper.setCompanyName(resultSet.getString("company_name"));
        Product product = new Product();
        product.setId(resultSet.getInt("p.id"));
        product.setTitle(resultSet.getString("p.title"));
        product.setDescription(resultSet.getString("description"));
        product.setPrice(resultSet.getFloat("price"));
        product.setDiscount(resultSet.getFloat("discount"));
        product.setCategory(category);
        product.setShipper(shipper);
        Customer customer = new Customer();
        customer.setUser(user);
        customer.setFullName(resultSet.getString("full_name"));
        Order order = new Order();
        order.setId(resultSet.getInt("o.id"));
        order.setProcessed(resultSet.getBoolean("is_processed"));
        order.setDate(resultSet.getDate("date").toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime());
        order.setCustomer(customer);
        ProductHasOrder productHasOrder = new ProductHasOrder();
        productHasOrder.setProduct(product);
        productHasOrder.setOrder(order);
        productHasOrder.setCount(resultSet.getInt("count"));
        return productHasOrder;
    }
}
