package ua.edu.sumdu.j2ee.zykov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.exception.CategoryNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.model.ImageToProduct;
import ua.edu.sumdu.j2ee.zykov.model.Product;
import ua.edu.sumdu.j2ee.zykov.service.CategoryService;
import ua.edu.sumdu.j2ee.zykov.service.ImageToProductService;

import java.util.List;

@RestController
@RequestMapping("/api/image_to_product")
@CrossOrigin(origins = "http://localhost:3000")
public class ImageToProductRestControllerApi {
    private static final Logger logger = LoggerFactory.getLogger(ImageToProductRestControllerApi.class);
    private final ImageToProductService imageToProductService;
    private final CategoryService categoryService;

    public ImageToProductRestControllerApi(ImageToProductService imageToProductService, CategoryService categoryService) {
        this.imageToProductService = imageToProductService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<ImageToProduct> getAllImageToProducts() {
        logger.info("Request to receive all images with products");
        List<ImageToProduct> imageToProducts = imageToProductService.getImageToProductsAll();
        return getImageToProducts(imageToProducts);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<ImageToProduct> getByProductIdImageToProduct(@RequestParam("product_id") int productId) {
        logger.info("Request to receive images for product {}", productId);
        List<ImageToProduct> imageToProducts = imageToProductService.getImageToProductsByProductId(productId);
        return getImageToProducts(imageToProducts);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ImageToProduct createImageToProduct(@RequestBody ImageToProduct imageToProduct) {
        logger.info("Request to create new images for product {}", imageToProduct);
        return imageToProductService.addImageToProduct(imageToProduct);
    }

    @RequestMapping(value = "/update/{imageId}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ImageToProduct updateImageToProduct(@PathVariable int imageId, @RequestBody ImageToProduct imageToProduct) {
        logger.info("Request to update image {} on {}", imageId, imageToProduct);
        return imageToProductService.updateImageToProduct(imageId, imageToProduct);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ImageToProduct removeImageToProduct(@RequestBody ImageToProduct imageToProduct) {
        logger.info("Request to remove images for product {}", imageToProduct);
        return imageToProductService.deleteImageToProduct(imageToProduct);
    }

    private List<ImageToProduct> getImageToProducts(List<ImageToProduct> imageToProducts) {
        Product product;
        for (ImageToProduct imageToProduct : imageToProducts) {
            product = imageToProduct.getProduct();
            product.setCategory(getCategory(product.getCategory().getId()));
        }
        return imageToProducts;
    }

    private Category getCategory(Integer categoryId) {
        try {
            return categoryService.getById(categoryId);
        } catch (CategoryNotExistException e) {
            return null;
        }
    }
}
