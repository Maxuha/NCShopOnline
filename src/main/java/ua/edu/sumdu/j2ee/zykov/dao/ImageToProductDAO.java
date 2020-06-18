package ua.edu.sumdu.j2ee.zykov.dao;

import ua.edu.sumdu.j2ee.zykov.model.ImageToProduct;

import java.util.List;

public interface ImageToProductDAO {
    List<ImageToProduct> findAll();
    List<ImageToProduct> findByProductId(int productId);
    ImageToProduct save(ImageToProduct imageToProduct);
    ImageToProduct update(ImageToProduct imageToProduct);
    ImageToProduct delete(ImageToProduct imageToProduct);
}
