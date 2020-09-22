package ua.edu.sumdu.j2ee.zykov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.exception.CategoryNotExistException;
import ua.edu.sumdu.j2ee.zykov.exception.NotExistException;
import ua.edu.sumdu.j2ee.zykov.exception.ProductNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.model.Product;
import ua.edu.sumdu.j2ee.zykov.model.ProductList;
import ua.edu.sumdu.j2ee.zykov.service.CategoryService;
import ua.edu.sumdu.j2ee.zykov.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductRestControllerApi {
    private static final Logger logger = LoggerFactory.getLogger(ProductRestControllerApi.class);
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductRestControllerApi(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductList getAllProducts(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortBy, @RequestParam String sortDir) {
        logger.info("Request to receive product by page {} and size {} and sort by {} in {}", page, size, sortBy, sortDir);
        ProductList productList = productService.getProductAllPagination(page, size, sortBy, sortDir);
        for (Product product : productList.getProducts()) {
            product.setCategory(getCategoryFromDb(product.getCategory().getId()));
        }
        return productList;
    }

    @RequestMapping(value = "/get/search", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getByTitleOrShipper(@RequestParam String searchText) {
        logger.info("Request to search products by title or shipper with search text: {}", searchText);
        List<Product> products = productService.getProductByTitleOrShipper(searchText);
        for (Product product : products) {
            product.setCategory(getCategoryFromDb(product.getCategory().getId()));
        }
        return products;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> getByIdProduct(@PathVariable int id) {
        logger.info("Request to receive product by id {}", id);
        ResponseEntity<?> responseEntity;
        try {
            Product product = productService.getProductById(id);
            product.setCategory(categoryService.getById(product.getCategory().getId()));
            responseEntity = ResponseEntity.ok().body(product);
        } catch (NotExistException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            logger.warn("Product by id {} not exist", id);
        }
        return responseEntity;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getByCategoryId(@RequestParam int categoryId) {
        logger.info("Request to receive products by category {}", categoryId);
        List<Product> products = productService.getProductByCategoryId(categoryId);
        for (Product product : products) {
            product.setCategory(getCategoryFromDb(product.getCategory().getId()));
        }
        return products;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Product createProduct(@RequestBody Product product) {
        logger.info("Request to create new product {}", product);
        return productService.addProduct(product);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        logger.info("Request to update product {}", product);
        ResponseEntity<?> responseEntity;
        try {
            Product productFromDb = productService.getProductById(product.getId());
            productFromDb.setCategory(product.getCategory());
            productFromDb.setDescription(product.getDescription());
            productFromDb.setTitle(product.getTitle());
            productFromDb.setDiscount(product.getDiscount());
            productFromDb.setPrice(product.getPrice());
            productFromDb.setShipper(product.getShipper());
            responseEntity = ResponseEntity.ok().body(productService.updateProduct(productFromDb));
        } catch (ProductNotExistException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            logger.warn("Product by id {} not exist", product.getId());
        }
        return responseEntity;
    }

    @RequestMapping(value = "/remove/{id}", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
        logger.info("Request to remove product {}", id);
        ResponseEntity<?> responseEntity;
        try {
            Product productFromDb = productService.getProductById(id);
            responseEntity = ResponseEntity.ok().body(productFromDb);
        } catch (ProductNotExistException e) {
            responseEntity = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            logger.warn("Product by id {} not exist", id);
        }
        return responseEntity;
    }

    private Category getCategoryFromDb(Integer categoryId) {
        try {
            return categoryService.getById(categoryId);
        } catch (CategoryNotExistException e) {
            return null;
        }
    }
}
