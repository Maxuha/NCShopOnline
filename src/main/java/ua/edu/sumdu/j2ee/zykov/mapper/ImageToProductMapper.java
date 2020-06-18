package ua.edu.sumdu.j2ee.zykov.mapper;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.zykov.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageToProductMapper implements RowMapper<ImageToProduct> {
    private final JdbcTemplate jdbcTemplate;

    public ImageToProductMapper(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public ImageToProduct mapRow(ResultSet resultSet, int i) throws SQLException {
        Image imageProduct = new Image();
        imageProduct.setId(resultSet.getInt("i.id"));
        imageProduct.setImage(resultSet.getString("i.image"));
        Image imageCategory = new Image();
        imageCategory.setId(resultSet.getInt("image.id"));
        imageCategory.setImage(resultSet.getString("image.image"));
        Category category = new Category();
        category.setId(resultSet.getInt("c.id"));
        category.setTitle(resultSet.getString("c.title"));
        category.setImage(imageCategory);
        category.setParent(null);
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setUserName(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        Shipper shipper = new Shipper();
        shipper.setUser(user);
        shipper.setCompanyName(resultSet.getString("company_name"));
        Product product = new Product();
        product.setId(resultSet.getInt("p.id"));
        product.setTitle(resultSet.getString("p.title"));
        product.setDescription(resultSet.getString("descriptiom"));
        product.setPrice(resultSet.getFloat("price"));
        product.setDiscount(resultSet.getFloat("discount"));
        product.setCategory(category);
        product.setShipper(shipper);
        ImageToProduct imageToProduct = new ImageToProduct();
        imageToProduct.setProduct(product);
        imageToProduct.setImage(imageProduct);
        return imageToProduct;
    }
}
