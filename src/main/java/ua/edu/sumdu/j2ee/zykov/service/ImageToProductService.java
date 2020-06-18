package ua.edu.sumdu.j2ee.zykov.service;

import ua.edu.sumdu.j2ee.zykov.model.ImageToProduct;

import java.util.List;

public interface ImageToProductService {
    List<ImageToProduct> getImageToProductsAll();
    List<ImageToProduct>  getImageToProductsByProductId(int productId);
    ImageToProduct addImageToProduct(ImageToProduct imageToProduct);
    ImageToProduct updateImageToProduct(ImageToProduct imageToProduct);
    ImageToProduct deleteImageToProduct(ImageToProduct imageToProduct);
}
