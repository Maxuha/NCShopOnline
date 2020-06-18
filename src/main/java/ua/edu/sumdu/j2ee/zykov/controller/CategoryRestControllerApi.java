package ua.edu.sumdu.j2ee.zykov.controller;

import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryRestControllerApi {
    private final CategoryService categoryService;

    public CategoryRestControllerApi(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(path = "/get/all", method = RequestMethod.GET)
    public String getAllCategories() {
        List<Category> categories = categoryService.getAll();
        return categories.toString();
    }

    @RequestMapping(path = "/get/{id}", method = RequestMethod.GET)
    public String getCategoryById(@PathVariable int id) {
        return "Hello, " + id;
    }

    @RequestMapping(path = "/create", method = RequestMethod.POST)
    public String createCategory(@RequestBody Category category) {
        return "Create, " + category;
    }

    @RequestMapping(path = "/update/{id}", method = RequestMethod.POST)
    public String createCategory(@PathVariable int id, @RequestBody Category category) {
        return "Update, " + category;
    }

    @RequestMapping(path = "/remove/{id}", method = RequestMethod.GET)
    public String deleteCategoryById(@PathVariable int id) {
        return "Delete, " + id;
    }
}
