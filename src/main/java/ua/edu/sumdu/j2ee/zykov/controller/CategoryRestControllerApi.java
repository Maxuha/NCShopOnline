package ua.edu.sumdu.j2ee.zykov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.model.CategoryList;
import ua.edu.sumdu.j2ee.zykov.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryRestControllerApi {
    private final CategoryService categoryService;

    public CategoryRestControllerApi(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategories() {
        return categoryService.getAll();
    }

    @RequestMapping(value = "/get/all/list", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public CategoryList getAllCategoriesPagination(@RequestParam Integer page, @RequestParam Integer size) {
        List<Category> categories = categoryService.getAllPagination(page, size);
        CategoryList categoryList = categoryService.getCountForCategory(size);
        categoryList.setCategories(categories);
        categoryList.setNumber(page);
        return categoryList;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getCategoriesByParentId(@RequestParam Integer parentId) {
        return categoryService.getByParentId(parentId);
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Category getByIdCategory(@PathVariable int id) {
        return categoryService.getById(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Category saveCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Category updateCategory(@RequestBody Category category) {
        return categoryService.updateCategory(category);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Category removeCategory(@RequestBody Category category) {
        return categoryService.deleteCategory(category);
    }
}
