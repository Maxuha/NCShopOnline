package ua.edu.sumdu.j2ee.zykov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.model.ImageToProduct;
import ua.edu.sumdu.j2ee.zykov.service.ImageToProductService;

import java.util.List;

@RestController
@RequestMapping("/api/image_to_product")
public class ImageToProductRestControllerApi {
    private final ImageToProductService imageToProductService;

    public ImageToProductRestControllerApi(ImageToProductService imageToProductService) {
        this.imageToProductService = imageToProductService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<ImageToProduct> getAllImageToProducts() {
        return imageToProductService.getImageToProductsAll();
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<ImageToProduct> getByProductIdImageToProduct(@RequestParam("product_id") int productId) {
        return imageToProductService.getImageToProductsByProductId(productId);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ImageToProduct createImageToProduct(@RequestBody ImageToProduct imageToProduct) {
        return imageToProductService.addImageToProduct(imageToProduct);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ImageToProduct updateImageToProduct(@RequestBody ImageToProduct imageToProduct) {
        return imageToProductService.updateImageToProduct(imageToProduct);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public ImageToProduct removeImageToProduct(@RequestBody ImageToProduct imageToProduct) {
        return imageToProductService.deleteImageToProduct(imageToProduct);
    }
}
