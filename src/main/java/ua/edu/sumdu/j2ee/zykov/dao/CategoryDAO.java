package ua.edu.sumdu.j2ee.zykov.dao;

import ua.edu.sumdu.j2ee.zykov.model.Category;

import java.util.List;

public interface CategoryDAO {
    List<Category> findAll();
}
