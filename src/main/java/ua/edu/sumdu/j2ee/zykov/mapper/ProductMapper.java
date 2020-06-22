package ua.edu.sumdu.j2ee.zykov.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.zykov.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Image image = new Image();
        image.setId(resultSet.getInt(14));
        image.setImage(resultSet.getString(15));
        User user = new User();
        user.setId(resultSet.getInt(16));
        user.setUserName(resultSet.getString(17));
        user.setPassword(resultSet.getString(18));
        Shipper shipper = new Shipper();
        shipper.setUser(user);
        shipper.setCompanyName(resultSet.getString(13));
        Category category = new Category();
        category.setId(resultSet.getInt(8));
        category.setImage(image);
        category.setTitle(resultSet.getString(9));
        category.setParent(null);
        Product product = new Product();
        product.setId(resultSet.getInt(1));
        product.setTitle(resultSet.getString(2));
        product.setDescription(resultSet.getString(3));
        product.setPrice(resultSet.getFloat(4));
        product.setDiscount(resultSet.getFloat(5));
        product.setCategory(category);
        product.setShipper(shipper);
        return product;
    }
}
