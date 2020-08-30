package ua.edu.sumdu.j2ee.zykov.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.zykov.model.CategoryList;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryListMapper implements RowMapper<CategoryList> {
    @Override
    public CategoryList mapRow(ResultSet resultSet, int i) throws SQLException {
        CategoryList categoryList = new CategoryList();
        categoryList.setTotalElements(resultSet.getInt(1));
        return categoryList;
    }
}
