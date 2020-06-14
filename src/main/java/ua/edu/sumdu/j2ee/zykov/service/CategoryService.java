package ua.edu.sumdu.j2ee.zykov.service;

import ua.edu.sumdu.j2ee.zykov.model.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category getById(int id);
    void create(Category category);
    void update(Category category);
    void delete(Category category);
}
