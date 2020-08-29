package ua.edu.sumdu.j2ee.zykov.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.zykov.model.ProductList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductListMapper implements RowMapper<ProductList> {
    @Override
    public ProductList mapRow(ResultSet resultSet, int i) throws SQLException {
        ProductList productList = new ProductList();
        productList.setTotalElements(resultSet.getInt(1));
        return productList;
    }
}
