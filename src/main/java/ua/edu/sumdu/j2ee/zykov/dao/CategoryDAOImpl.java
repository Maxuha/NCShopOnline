package ua.edu.sumdu.j2ee.zykov.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.edu.sumdu.j2ee.zykov.exception.CategoryNotExistException;
import ua.edu.sumdu.j2ee.zykov.mapper.CategoryListMapper;
import ua.edu.sumdu.j2ee.zykov.mapper.CategoryMapper;
import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.model.CategoryList;
import ua.edu.sumdu.j2ee.zykov.model.EntityField;
import ua.edu.sumdu.j2ee.zykov.model.Image;

import java.util.List;
import java.util.Optional;

@Repository
public class CategoryDAOImpl implements CategoryDAO {
    private final JdbcTemplate jdbcTemplate;
    private EntityField entityField;

    public CategoryDAOImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Category> findAll() {
        String query = "SELECT * FROM category LEFT JOIN image ON category.image_id = image.id ORDER BY category.id";
        return jdbcTemplate.query(query, new CategoryMapper());
    }

    @Override
    public List<Category> findByParentId(int parentId) throws CategoryNotExistException {
        String fieldParentId = "parent id";
        entityField = new EntityField(parentId, fieldParentId);
        List<Category> categories;
        if (parentId == 0) {
            String query = "SELECT * FROM category LEFT JOIN image ON category.image_id = image.id WHERE category.parent_id is null ORDER BY category.id;";
            categories = findWithoutParent(query);
        } else {
            String query = "SELECT * FROM category LEFT JOIN image ON category.image_id = image.id WHERE category.parent_id = ? ORDER BY category.id;";
            categories = findWithParent(query);
        }
        return categories;
    }

    private List<Category> findWithoutParent(String query) throws CategoryNotExistException {
        List<Category> categories = jdbcTemplate.query(query, new CategoryMapper());
        if (categories.size() == 0) {
            throw new CategoryNotExistException(entityField);
        }
        return categories;
    }

    private List<Category> findWithParent(String query) throws CategoryNotExistException {
        List<Category> categories = jdbcTemplate.query(query, new CategoryMapper(), entityField.getId());
        if (categories.size() == 0) {
            throw new CategoryNotExistException(entityField);
        }
        return categories;
    }

    @Override
    public CategoryList findCategoryListWithTotalElements() {
        String sql = "SELECT count(*) FROM category";
        return jdbcTemplate.queryForObject(sql, new CategoryListMapper());
    }

    @Override
    public Category findById(int id) throws CategoryNotExistException {
        entityField = new EntityField(id, "id");
        String query = "SELECT * FROM category LEFT JOIN image ON category.image_id = image.id";
        List<Category> categories = jdbcTemplate.query(query, new CategoryMapper());
        return findTargetCategory(categories, id);
    }

    private Category findTargetCategory(List<Category> categories, int targetId) throws CategoryNotExistException {
        Category category = null;
        for (Category tempCategory : categories) {
            if (tempCategory.getId() == targetId) {
                category = tempCategory;
            }
        }
        if (category == null) {
            throw new CategoryNotExistException(entityField);
        }
        return category;
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
