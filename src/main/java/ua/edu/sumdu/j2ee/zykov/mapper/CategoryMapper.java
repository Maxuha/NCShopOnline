package ua.edu.sumdu.j2ee.zykov.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.zykov.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<Category> {
    Category parent = null;
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setTitle(resultSet.getString("title"));
        category.setImageToPath(resultSet.getString("path_to_image"));
        category.setParent(parent);
        int parentId = resultSet.getInt("parent");
        if (parent == null || parentId != parent.getId()) {
            parent = category;
        }
        return category;
    }
}
