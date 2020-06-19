package ua.edu.sumdu.j2ee.zykov.mapper;

import org.springframework.jdbc.core.RowMapper;
import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.model.Image;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryMapper implements RowMapper<Category> {
    List<Category> categories = new ArrayList<>();
    @Override
    public Category mapRow(ResultSet resultSet, int i) throws SQLException {
        Image image = new Image();
        image.setId(resultSet.getInt(5));
        image.setImage(resultSet.getString(6));
        Category category = new Category();
        category.setId(resultSet.getInt(1));
        category.setTitle(resultSet.getString(2));
        category.setImage(image);
        int parentId = resultSet.getInt(4);
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