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
        String query = "SELECT * FROM category WHERE parent_id is null";
        List<Category> mainCategory = jdbcTemplate.query(query, new CategoryMapper());
        List<Category> categories = new ArrayList<>();
        for (Category category : mainCategory) {
            query = "WITH RECURSIVE r AS (" +
                    "    SELECT id, title, image_id, parent_id" +
                    "    FROM category" +
                    "    WHERE id = ?" +
                    "    UNION" +
                    "    SELECT category.id, category.title, category.image_id, category.parent_id" +
                    "    FROM category" +
                    "             JOIN r" +
                    "                  ON category.parent_id = r.id" +
                    ")" +
                    "SELECT * FROM r " +
                    "   LEFT JOIN image " +
                    "       ON r.image_id = image.id;";
            categories.addAll(jdbcTemplate.query(query, new CategoryMapper(), category.getId()));
        }
        return categories;
    }

    @Override
    public Category findById(int id) {
        String query = "WITH RECURSIVE r AS (" +
                "    SELECT id, title, image_id, parent_id" +
                "    FROM category" +
                "    WHERE id = ?" +
                "    UNION" +
                "    SELECT category.id, category.title, category.image_id, category.parent_id" +
                "    FROM category" +
                "             JOIN r" +
                "                  ON category.id = r.parent_id" +
                ")" +
                "SELECT * FROM r" +
                "   LEFT JOIN image" +
                "       ON r.image_id = image.id;";
        List<Category> categories = jdbcTemplate.query(query, new CategoryMapper(), id);
        for (int i = 0; i < categories.size() - 1; i++) {
            categories.get(i).setParent(categories.get(i + 1));
        }
        return categories.get(0);
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
