package ua.edu.sumdu.j2ee.zykov.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.zykov.model.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ImageToProductMapper implements RowMapper<ImageToProduct> {
    @Override
    public ImageToProduct mapRow(ResultSet resultSet, int i) throws SQLException {
        Image imageProduct = new Image();
        imageProduct.setId(resultSet.getInt(3));
        imageProduct.setImage(resultSet.getString(4));
        Image imageCategory = new Image();
        imageCategory.setId(resultSet.getInt(21));
        imageCategory.setImage(resultSet.getString(22));
        Category category = new Category();
        category.setId(resultSet.getInt(14));
        category.setTitle(resultSet.getString(15));
        category.setImage(imageCategory);
        category.setParent(null);
        User user = new User();
        user.setId(resultSet.getInt(18));
        user.setUserName(resultSet.getString(19));
        user.setPassword(resultSet.getString(20));
        Shipper shipper = new Shipper();
        shipper.setUser(user);
        shipper.setCompanyName(resultSet.getString(13));
        Product product = new Product();
        product.setId(resultSet.getInt(5));
        product.setTitle(resultSet.getString(6));
        product.setDescription(resultSet.getString(7));
        product.setPrice(resultSet.getFloat(8));
        product.setDiscount(resultSet.getFloat(9));
        product.setCategory(category);
        product.setShipper(shipper);
        ImageToProduct imageToProduct = new ImageToProduct();
        imageToProduct.setProduct(product);
        imageToProduct.setImage(imageProduct);
        return imageToProduct;
    }
}
