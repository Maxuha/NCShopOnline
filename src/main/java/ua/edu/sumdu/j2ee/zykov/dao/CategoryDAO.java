package ua.edu.sumdu.j2ee.zykov.dao;

import ua.edu.sumdu.j2ee.zykov.exception.CategoryNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.model.CategoryList;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
    List<Category> findByParentId(int parentId) throws CategoryNotExistException;
    CategoryList findCategoryListWithTotalElements();
    Category findById(int id) throws CategoryNotExistException;
    Category save(Category category);
    Category update(Category category);
    Category delete(Category category);
}
