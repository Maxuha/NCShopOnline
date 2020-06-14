package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.mapper.CategoryMapper;
import ua.edu.sumdu.j2ee.zykov.model.Category;

import javax.xml.transform.sax.SAXResult;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private final DAOConnection daoConnection;
    List<Category> categories;

    public CategoryDAOImpl(DAOConnection daoConnection) {
        this.daoConnection = daoConnection;
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
        Connection connection = daoConnection.connect();
        categories = new ArrayList<>();
        try {
            daoConnection.setStatement((PreparedStatement) connection.createStatement());
            daoConnection.setResultSet(daoConnection.getStatement().executeQuery("SELECT * FROM category"));
            while (daoConnection.getResultSet().next()) {
                categories.add(parseCategory(daoConnection.getResultSet()));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        finally {
            daoConnection.disconnect();
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
    public void create(Category category) {
        Connection connection = daoConnection.connect();
        try {
            daoConnection.setStatement(connection.prepareStatement("INSERT INTO category (title, path_to_image, parent) VALUES (?, ?, ?)"));
            daoConnection.getStatement().setString(1, category.getTitle());
            daoConnection.getStatement().setString(2, category.getImageToPath());
            daoConnection.getStatement().setInt(3, category.getId());
            daoConnection.getStatement().execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            daoConnection.disconnect();
        }
    }


    private Category parseCategory(ResultSet resultSet) {
        Category category = null;
        try {
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String pathToImage = resultSet.getString("path_to_image");
            Integer parentId = resultSet.getInt("parent");
            category = findCategoryByIdFromList(id);
            if (parentId != null) {
                Category parent = findCategoryByIdFromList(parentId);
                if (parent == null) {
                    parent = new Category(parentId);
                    categories.add(parent);
                }
                if (category == null) {
                    category = new Category(id, title, pathToImage, parent);
                } else {
                    category.setTitle(title);
                    category.setImageToPath(pathToImage);
                    category.setParent(parent);
                }
            }
            if (category == null) {
                category = new Category(id, title, pathToImage, null);
            } else {
                category.setTitle(title);
                category.setImageToPath(pathToImage);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return category;
    }

    private Category findCategoryByIdFromList(int id) {
        for (Category category : categories) {
            if (category.getId() == id) {
                return category;
            }
        }
        return null;
    }
}
