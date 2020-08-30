package ua.edu.sumdu.j2ee.zykov.dao;

import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.model.CategoryList;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
    List<Category> findByParentId(int parentId);
    Category findById(int id);
    Category save(Category category);
    Category update(Category category);
    Category delete(Category category);
    CategoryList getCountForCategory();
}
