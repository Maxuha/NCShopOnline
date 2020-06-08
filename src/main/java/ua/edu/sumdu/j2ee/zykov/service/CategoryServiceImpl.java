package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.zykov.dao.CategoryDAO;
import ua.edu.sumdu.j2ee.zykov.model.Category;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO categoryDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
    }

    @Override
    public List<Category> getAll() {
        return categoryDAO.findAll();
    }

    @Override
    public void create(Category category) {
        categoryDAO.save(category);
    }

    @Override
    public void update(Category category) {
        categoryDAO.update(category);
    }

    @Override
    public void delete(Category category) {
        categoryDAO.delete(category);
    }
}
