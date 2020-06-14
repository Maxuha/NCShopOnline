package ua.edu.sumdu.j2ee.zykov.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.zykov.model.Category;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryMapper implements RowMapper<Category> {
    List<Category> categories = new ArrayList<>();
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Category category = new Category();
        category.setId(resultSet.getInt("id"));
        category.setTitle(resultSet.getString("title"));
        category.setImageToPath(resultSet.getString("path_to_image"));
        int parentId = resultSet.getInt("parent");
        category.setParent(findParentCategoryByIdFromList(parentId));
        categories.add(category);
        return category;
    }

    private Category findParentCategoryByIdFromList(int parentId) {
        for (Category category : categories) {
            if (category.getId() == parentId) {
                return category;
            }
        }
        return null;
    }
}