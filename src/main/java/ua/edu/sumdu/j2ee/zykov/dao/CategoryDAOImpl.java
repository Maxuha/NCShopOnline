package ua.edu.sumdu.j2ee.zykov.dao;

import org.apache.coyote.http11.filters.IdentityOutputFilter;
import ua.edu.sumdu.j2ee.zykov.model.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
            daoConnection.setStatement(connection.createStatement());
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
            daoConnection.setStatement(connection.prepareStatement("INSERT INTO Category (id, title, path_to_image, parent) VALUES (?, ?, ?, ?)"));
            ((PreparedStatement) daoConnection.getStatement()).setInt(1, category.getId());
            ((PreparedStatement) daoConnection.getStatement()).setString(1, category.getTitle());
            ((PreparedStatement) daoConnection.getStatement()).setString(1, category.getImageToPath());
            ((PreparedStatement) daoConnection.getStatement()).setInt(1, category.getId());
            ((PreparedStatement) daoConnection).execute();
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
            String pathToImage = resultSet.getString("image_to_path");
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
