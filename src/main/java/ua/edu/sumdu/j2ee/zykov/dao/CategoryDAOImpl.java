package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private final DAOConnection daoConnection;
    List<Category> categories;

    public CategoryDAOImpl(DAOConnection daoConnection) {
        this.daoConnection = daoConnection;
    }

    @Override
    public List<Category> findAll() {
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
    public void create(Category category) {
        Connection connection = daoConnection.connect();
        try {
            daoConnection.setStatement(connection.prepareStatement("INSERT INTO category (id, title, path_to_image, parent) VALUES (?, ?, ?, ?)"));
            daoConnection.getStatement().setInt(1, category.getId());
            daoConnection.getStatement().setString(2, category.getTitle());
            daoConnection.getStatement().setString(3, category.getImageToPath());
            daoConnection.getStatement().setInt(4, category.getId());
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
