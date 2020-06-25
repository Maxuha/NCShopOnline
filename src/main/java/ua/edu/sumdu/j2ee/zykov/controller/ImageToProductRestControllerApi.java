package ua.edu.sumdu.j2ee.zykov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.model.Category;
import ua.edu.sumdu.j2ee.zykov.model.Image;
import ua.edu.sumdu.j2ee.zykov.model.ImageToProduct;
import ua.edu.sumdu.j2ee.zykov.model.Product;
import ua.edu.sumdu.j2ee.zykov.service.CategoryService;
import ua.edu.sumdu.j2ee.zykov.service.ImageToProductService;

import java.util.List;

@RestController
@RequestMapping("/api/image_to_product")
public class ImageToProductRestControllerApi {
    private final ImageToProductService imageToProductService;
    private final CategoryService categoryService;

    public ImageToProductRestControllerApi(ImageToProductService imageToProductService, CategoryService categoryService) {
        this.imageToProductService = imageToProductService;
        this.categoryService = categoryService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<ImageToProduct> getAllImageToProducts() {
        List<ImageToProduct> imageToProducts = imageToProductService.getImageToProductsAll();
        return getImageToProducts(imageToProducts);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<ImageToProduct> getByProductIdImageToProduct(@RequestParam("product_id") int productId) {
        List<ImageToProduct> imageToProducts = imageToProductService.getImageToProductsByProductId(productId);
        return getImageToProducts(imageToProducts);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ImageToProduct createImageToProduct(@RequestBody ImageToProduct imageToProduct) {
        return imageToProductService.addImageToProduct(imageToProduct);
    }

    @RequestMapping(value = "/update/{imageId}", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ImageToProduct updateImageToProduct(@PathVariable int imageId, @RequestBody ImageToProduct imageToProduct) {
        return imageToProductService.updateImageToProduct(imageId, imageToProduct);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ImageToProduct removeImageToProduct(@RequestBody ImageToProduct imageToProduct) {
        return imageToProductService.deleteImageToProduct(imageToProduct);
    }

    private List<ImageToProduct> getImageToProducts(List<ImageToProduct> imageToProducts) {
        Product product;
        Category category;
        for (ImageToProduct imageToProduct : imageToProducts) {
            product = imageToProduct.getProduct();
            category = product.getCategory();
            category = categoryService.getById(category.getId());
            product.setCategory(category);
        }
        return imageToProducts;
    }
}
