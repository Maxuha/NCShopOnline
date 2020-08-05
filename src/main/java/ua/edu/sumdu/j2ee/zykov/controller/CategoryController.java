package ua.edu.sumdu.j2ee.zykov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.edu.sumdu.j2ee.zykov.dao.CategoryDAO;
import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.service.CategoryService;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping("/create")
    public String createCategory() {
        return "categories";
    }

    @RequestMapping("/products")
    public String products() {
        return "products";
    }

    @RequestMapping()
    public String showAllCategories() {
        return "categories";
    }
}
