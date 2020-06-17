package ua.edu.sumdu.j2ee.zykov.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.zykov.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Image image = new Image();
        image.setId(resultSet.getInt("i.id"));
        image.setImage(resultSet.getString("image"));
        User user = new User();
        user.setId(resultSet.getInt("u.id"));
        user.setUserName(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        Shipper shipper = new Shipper();
        shipper.setUser(user);
        shipper.setCompanyName(resultSet.getString("company_name"));
        Category category = new Category();
        category.setId(resultSet.getInt("c.id"));
        category.setImage(image);
        category.setTitle(resultSet.getString("c.title"));
        category.setParent(null);
        Product product = new Product();
        product.setId(resultSet.getInt("product.id"));
        product.setTitle(resultSet.getString("product.title"));
        product.setDescription(resultSet.getNString("description"));
        product.setPrice(resultSet.getFloat("price"));
        product.setDiscount(resultSet.getFloat("discount"));
        product.setCategory(category);
        product.setShipper(shipper);
        return product;
    }
}
