package ua.edu.sumdu.j2ee.zykov.service;

import ua.edu.sumdu.j2ee.zykov.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    List<Category> getByParentId(int parentId);
    Category getById(int id);
    Category addCategory(Category category);
    Category updateCategory(Category category);
    Category deleteCategory(Category category);
}
