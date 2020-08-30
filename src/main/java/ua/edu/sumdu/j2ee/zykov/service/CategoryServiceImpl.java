package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.zykov.dao.CategoryDAO;
import ua.edu.sumdu.j2ee.zykov.dao.ImageDAO;
import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.model.CategoryList;
import ua.edu.sumdu.j2ee.zykov.model.Image;
import ua.edu.sumdu.j2ee.zykov.model.Product;

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
    public List<Category> getAll(int page, int size) {
        List<Category> categories = categoryDAO.findAll();
        int from = page * size;
        int to = from + size;
        to = Math.min(to, categories.size());
        return categories.subList(from, to);
    }

    @Override
    public List<Category> getByParentId(int parentId) {
        return categoryDAO.findByParentId(parentId);
    }

    @Override
    public Category getById(int id) {
        return categoryDAO.findById(id);
    }

    @Override
    public Category addCategory(Category category) {
        return categoryDAO.save(getValidCategory(category));
    }

    @Override
    public Category updateCategory(Category category) {
        return categoryDAO.update(getValidCategory(category));
    }

    @Override
    public Category deleteCategory(Category category) {
        return categoryDAO.delete(category);
    }

    @Override
    public CategoryList getCountForCategory(int size) {
        CategoryList categoryList =  categoryDAO.getCountForCategory();
        int totalPages = (int) Math.ceil(categoryList.getTotalElements() / (double)size);
        categoryList.setTotalPages(totalPages);
        return categoryList;
    }

    private Category getValidCategory(Category category) {
        Image image = imageDAO.save(category.getImage() != null ? category.getImage() : new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSBWCN9Acq8fXUM4G4e3c9l--1RWCkVX9folw&usqp=CAU"));
        category.setImage(image);
        return category;
    }
}
