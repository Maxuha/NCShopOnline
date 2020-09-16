package ua.edu.sumdu.j2ee.zykov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.model.Product;
import ua.edu.sumdu.j2ee.zykov.model.ProductHasOrder;
import ua.edu.sumdu.j2ee.zykov.service.CategoryService;
import ua.edu.sumdu.j2ee.zykov.service.ProductHasOrderService;

import java.util.List;

@RestController
@RequestMapping("/api/product_has_order")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductHasOrderRestControllerApi {
    private static final Logger logger = LoggerFactory.getLogger(ProductHasOrderRestControllerApi.class);
    private final ProductHasOrderService productHasOrderService;
    private final CategoryService categoryService;

    public ProductHasOrderRestControllerApi(ProductHasOrderService productHasOrderService, CategoryService categoryService) {
        this.productHasOrderService = productHasOrderService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductHasOrder> getAllProductHasOrders() {
        logger.info("Request to receive products has orders");
        List<ProductHasOrder> productHasOrders = productHasOrderService.getProductHasOrderAll();
        return getProductHasOrders(productHasOrders);
    }

    @RequestMapping(value = "/get/by_product/{productId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductHasOrder> getByProductIdProductHasOrder(@PathVariable int productId) {
        logger.info("Request to receive orders by product {}", productId);
        List<ProductHasOrder> productHasOrders = productHasOrderService.getProductHasOrderByProduct(productId);
        return getProductHasOrders(productHasOrders);
    }

    @RequestMapping(value = "/get/by_order/{orderId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductHasOrder> getByOrderIdProductHasOrder(@PathVariable int orderId) {
        logger.info("Request to receive products by order {}", orderId);
        List<ProductHasOrder> productHasOrders = productHasOrderService.getProductHasOrderByOrder(orderId);
        return getProductHasOrders(productHasOrders);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductHasOrder createProductHasOrder(@RequestBody ProductHasOrder productHasOrder) {
        logger.info("Request to create new order has product {}", productHasOrder);
        return productHasOrderService.addProductHasOrder(productHasOrder);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductHasOrder updateProductHasOrder(@RequestBody ProductHasOrder productHasOrder) {
        logger.info("Request to update order has product {}", productHasOrder);
        return productHasOrderService.updateProductHasOrder(productHasOrder);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductHasOrder deleteProductHasOrder(@RequestBody ProductHasOrder productHasOrder) {
        logger.info("Request to remove product has order");
        return productHasOrderService.deleteProductHasOrder(productHasOrder);
    }

    private List<ProductHasOrder> getProductHasOrders(List<ProductHasOrder> productHasOrders) {
        Product product;
        Category category;
        for (ProductHasOrder productHasOrder : productHasOrders) {
            product = productHasOrder.getProduct();
            category = product.getCategory();
            category = categoryService.getById(category.getId());
            product.setCategory(category);
            productHasOrder.setProduct(product);
        }
        return productHasOrders;
    }
}
