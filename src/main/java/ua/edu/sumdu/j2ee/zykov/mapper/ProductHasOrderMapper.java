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
        user.setId(resultSet.getInt(15));
        user.setUserName(resultSet.getString(16));
        user.setPassword(resultSet.getString(17));
        Image image = new Image();
        image.setId(resultSet.getInt(22));
        image.setImage(resultSet.getString(23));
        Category category = new Category();
        category.setId(resultSet.getInt(18));
        category.setTitle(resultSet.getString(19));
        category.setImage(image);
        category.setParent(null);
        Shipper shipper = new Shipper();
        shipper.setUser(user);
        shipper.setCompanyName(resultSet.getString(25));
        Product product = new Product();
        product.setId(resultSet.getInt(8));
        product.setTitle(resultSet.getString(9));
        product.setDescription(resultSet.getString(10));
        product.setPrice(resultSet.getFloat(11));
        product.setDiscount(resultSet.getFloat(12));
        product.setCategory(category);
        product.setShipper(shipper);
        Customer customer = new Customer();
        customer.setUser(user);
        customer.setFullName(resultSet.getString(27));
        Order order = new Order();
        order.setId(resultSet.getInt(4));
        order.setProcessed(resultSet.getBoolean(5));
        order.setDate(resultSet.getTimestamp(3).toLocalDateTime());
        order.setCustomer(customer);
        ProductHasOrder productHasOrder = new ProductHasOrder();
        productHasOrder.setProduct(product);
        productHasOrder.setOrder(order);
        productHasOrder.setCount(resultSet.getInt(3));
        return productHasOrder;
    }
}
