package ua.edu.sumdu.j2ee.zykov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.exception.CategoryNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.model.CategoryList;
import ua.edu.sumdu.j2ee.zykov.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin(origins = "http://localhost:3000")
public class CategoryRestControllerApi {
    private static final Logger logger = LoggerFactory.getLogger(CategoryRestControllerApi.class);
    private final CategoryService categoryService;

    public CategoryRestControllerApi(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Category> getAllCategories() {
        logger.info("Request to receive all categories");
        return categoryService.getAll();
    }

    @RequestMapping(value = "/get/all/list", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public CategoryList getAllCategoriesPagination(@RequestParam Integer page, @RequestParam Integer size) {
        logger.info("Request to receive all categories by page {} and size {}", page, size);
        return categoryService.getAllPagination(page, size);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getCategoriesByParentId(@RequestParam Integer parentId) {
        logger.info("Request to receive categories by parent {}", parentId);
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = ResponseEntity.ok().body(categoryService.getByParentId(parentId));
        } catch (CategoryNotExistException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getByIdCategory(@PathVariable int id) {
        logger.info("Request to receive category by id {}", id);
        ResponseEntity<?> responseEntity;
        try {
            responseEntity = ResponseEntity.ok().body(categoryService.getById(id));
        } catch (CategoryNotExistException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Category saveCategory(@RequestBody Category category) {
        logger.info("Request to create new category {}", category);
        return categoryService.addCategory(category);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateCategory(@RequestBody Category category) {
        logger.info("Request to update category {}", category);
        ResponseEntity<?> responseEntity;
        try {
            Category categoryFromDb = categoryService.getById(category.getId());
            categoryFromDb.setTitle(category.getTitle());
            categoryFromDb.setImage(category.getImage());
            categoryFromDb.setParent(category.getParent());
            responseEntity = ResponseEntity.ok().body(categoryService.updateCategory(categoryFromDb));
        } catch (CategoryNotExistException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?>  removeCategory(@PathVariable Integer id) {
        logger.info("Request to delete category {}", id);
        ResponseEntity<?> responseEntity;
        try {
            Category categoryFromDb = categoryService.getById(id);
            responseEntity = ResponseEntity.ok().body(categoryService.deleteCategory(categoryFromDb));
        } catch (CategoryNotExistException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return responseEntity;
    }
}
