package ua.edu.sumdu.j2ee.zykov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.model.Product;
import ua.edu.sumdu.j2ee.zykov.model.ProductHasOrder;
import ua.edu.sumdu.j2ee.zykov.service.ProductHasOrderService;

import java.util.List;

@RestController
@RequestMapping("/api/product_has_order")
public class ProductHasOrderRestControllerApi {
    private final ProductHasOrderService productHasOrderService;

    public ProductHasOrderRestControllerApi(ProductHasOrderService productHasOrderService) {
        this.productHasOrderService = productHasOrderService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductHasOrder> getAllProductHasOrders() {
        return productHasOrderService.getProductHasOrderAll();
    }

    @RequestMapping(value = "/get/by_product/{productId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductHasOrder> getByProductIdProductHasOrder(@PathVariable int productId) {
        return productHasOrderService.getProductHasOrderByProduct(productId);
    }

    @RequestMapping(value = "/get/by_order/{orderId}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<ProductHasOrder> getByOrderIdProductHasOrder(@PathVariable int orderId) {
        return productHasOrderService.getProductHasOrderByOrder(orderId);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductHasOrder createProductHasOrder(@RequestBody ProductHasOrder productHasOrder) {
        return productHasOrderService.addProductHasOrder(productHasOrder);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductHasOrder updateProductHasOrder(@RequestBody ProductHasOrder productHasOrder) {
        return productHasOrderService.updateProductHasOrder(productHasOrder);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ProductHasOrder deleteProductHasOrder(@RequestBody ProductHasOrder productHasOrder) {
        return productHasOrderService.deleteProductHasOrder(productHasOrder);
    }
}
