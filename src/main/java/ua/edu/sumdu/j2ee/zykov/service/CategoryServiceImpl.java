package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.zykov.dao.CategoryDAO;
import ua.edu.sumdu.j2ee.zykov.dao.ImageDAO;
import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.model.Image;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDAO categoryDAO;
    private final ImageDAO imageDAO;

    public CategoryServiceImpl(CategoryDAO categoryDAO, ImageDAO imageDAO) {
        this.categoryDAO = categoryDAO;
        this.imageDAO = imageDAO;
    }

    @Override
    public List<Category> getAll() {
        return categoryDAO.findAll();
    }

    @Override
    public Category getById(int id) {
        return categoryDAO.findById(id);
    }

    @Override
    public Category addCategory(Category category) {
        Image image = imageDAO.save(category.getImage());
        category.setImage(image);
        return categoryDAO.save(category);
    }

    @Override
    public Category updateCategory(Category category) {
        Image image = imageDAO.save(category.getImage());
        category.setImage(image);
        return categoryDAO.update(category);
    }

    @Override
    public Category deleteCategory(Category category) {
        return categoryDAO.delete(category);
    }
}
