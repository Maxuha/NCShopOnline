package ua.edu.sumdu.j2ee.zykov.service;

import ua.edu.sumdu.j2ee.zykov.exception.CategoryNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.model.CategoryList;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    CategoryList getAllPagination(int page, int size);
    List<Category> getByParentId(int parentId) throws CategoryNotExistException;
    Category getById(int id) throws CategoryNotExistException;
    Category addCategory(Category category);
    Category updateCategory(Category category);
    Category deleteCategory(Category category);
}
