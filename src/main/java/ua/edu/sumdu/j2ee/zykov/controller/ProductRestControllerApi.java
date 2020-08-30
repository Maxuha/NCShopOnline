package ua.edu.sumdu.j2ee.zykov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.model.Product;
import ua.edu.sumdu.j2ee.zykov.model.ProductList;
import ua.edu.sumdu.j2ee.zykov.service.CategoryService;
import ua.edu.sumdu.j2ee.zykov.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductRestControllerApi {
    private final ProductService productService;
    private final CategoryService categoryService;

    public ProductRestControllerApi(ProductService productService, CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductList getAllProducts(@RequestParam Integer page, @RequestParam Integer size, @RequestParam String sortBy, @RequestParam String sortDir) {
        List<Product> products = productService.getProductAll(page, size, sortBy, sortDir);
        for (Product product : products) {
            product.setCategory(categoryService.getById(product.getCategory().getId()));
        }
        ProductList productList = productService.getCountForProduct(size);
        productList.setProducts(products);
        productList.setNumber(page);
        return productList;
    }

    @RequestMapping(value = "/get/search", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getByTitleOrShipper(@RequestParam String searchText) {
        List<Product> products = productService.getProductByTitleOrShipper(searchText);
        for (Product product : products) {
            product.setCategory(categoryService.getById(product.getCategory().getId()));
        }
        return products;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Product getByIdProduct(@PathVariable int id) {
        Product product = productService.getProductById(id);
        product.setCategory(categoryService.getById(product.getCategory().getId()));
        return product;
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getByCategoryId(@RequestParam int categoryId) {
        List<Product> products = productService.getProductByCategoryId(categoryId);
        for (Product product : products) {
            product.setCategory(categoryService.getById(product.getCategory().getId()));
        }
        return products;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Product createProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Product updateProduct(@RequestBody Product product) {
        return productService.updateProduct(product);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Product deleteProduct(@RequestBody Product product) {
        return productService.deleteProduct(product);
    }
}
