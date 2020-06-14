package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.mapper.CategoryMapper;
import ua.edu.sumdu.j2ee.zykov.model.Category;

import javax.xml.transform.sax.SAXResult;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        String query = "SELECT * FROM category WHERE parent is null";
        List<Category> mainCategory = jdbcTemplate.query(query, new CategoryMapper());
        List<Category> categories = new ArrayList<>();
        for (Category category : mainCategory) {
            query = "WITH RECURSIVE r AS (" +
                    "    SELECT id, title, path_to_image, parent, 1 AS level" +
                    "    FROM category" +
                    "    WHERE id = ?" +
                    "    UNION" +
                    "    SELECT category.id, category.title, category.path_to_image, category.parent, r.level + 1 AS level" +
                    "    FROM category" +
                    "             JOIN r" +
                    "                  ON category.parent = r.id" +
                    ")" +
                    "SELECT * FROM r;";
            categories.addAll(jdbcTemplate.query(query, new CategoryMapper(), category.getId()));
        }
        return categories;
    }

    @Override
    public Category findById(int id) {
        String query = "WITH RECURSIVE r AS (" +
                "    SELECT id, title, path_to_image, parent, 1 AS level" +
                "    FROM category" +
                "    WHERE id = ?" +
                "    UNION" +
                "    SELECT category.id, category.title, category.path_to_image, category.parent, r.level + 1 AS level" +
                "    FROM category" +
                "             JOIN r" +
                "                  ON category.id = r.parent" +
                ")" +
                "SELECT * FROM r;";
        List<Category> categories = jdbcTemplate.query(query, new CategoryMapper(), id);
        for (int i = 0; i < categories.size() - 1; i++) {
            categories.get(i).setParent(categories.get(i + 1));
        }
        return categories.get(0);
    }

    @Override
    public void save(Category category) {
        String query = "INSERT INTO category (title, path_to_image, parent) VALUES (?, ?, ?)";
        Optional<Category> parent = Optional.ofNullable(category.getParent());
        jdbcTemplate.update(query, category.getTitle(), category.getImageToPath(), parent.isPresent() ? parent.get().getId() : null);
    }

    @Override
    public void update(Category category) {
        String query = "UPDATE category SET title = ?, path_to_image = ?, parent = ? WHERE id = ?";
        Optional<Category> parent = Optional.ofNullable(category.getParent());
        jdbcTemplate.update(query, category.getTitle(), category.getImageToPath(), parent.isPresent() ? parent.get().getId() : null, category.getId());
    }

    @Override
    public void delete(Category category) {
        String query = "DELETE FROM category WHERE id = ? ";
        jdbcTemplate.update(query, category.getId());
    }
}
