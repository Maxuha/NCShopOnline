package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.mapper.CategoryMapper;
import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.model.Image;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private final JdbcTemplate jdbcTemplate;

    public CategoryDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> findAll() {
        String query = "SELECT * FROM category LEFT JOIN image ON category.image_id = image.id ORDER BY category.id";
        List<Category> categories = jdbcTemplate.query(query, new CategoryMapper());
        return categories;
    }

    @Override
    public Category findById(int id) {
        String query = "SELECT * FROM category LEFT JOIN image ON category.image_id = image.id ORDER BY category.id";
        List<Category> categories = jdbcTemplate.query(query, new CategoryMapper());
        return categories.get(id - 1);
    }

    @Override
    public Category save(Category category) {
        String query = "INSERT INTO category (title, image_id, parent_id) VALUES (?, ?, ?)";
        Optional<Category> parent = Optional.ofNullable(category.getParent());
        Optional<Image> image = Optional.ofNullable(category.getImage());
        jdbcTemplate.update(query, category.getTitle(), image.isPresent() ? image.get().getId() : null, parent.isPresent() ? parent.get().getId() : null);
        return category;
    }

    @Override
    public Category update(Category category) {
        String query = "UPDATE category SET title = ?, image_id = ?, parent_id = ? WHERE id = ?";
        Optional<Category> parent = Optional.ofNullable(category.getParent());
        Optional<Image> image = Optional.ofNullable(category.getImage());
        jdbcTemplate.update(query, category.getTitle(), image.isPresent() ? image.get().getId() : null, parent.isPresent() ? parent.get().getId() : null, category.getId());
        return category;
    }

    @Override
    public Category delete(Category category) {
        String query = "DELETE FROM category WHERE id = ? ";
        jdbcTemplate.update(query, category.getId());
        return category;
    }
}
