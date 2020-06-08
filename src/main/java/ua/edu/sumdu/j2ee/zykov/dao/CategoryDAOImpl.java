package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
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
    List<Category> categories;

    public CategoryDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> findAll() {
        categories = new ArrayList<>();
        String query = "SELECT * FROM category";

        return categories;
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
