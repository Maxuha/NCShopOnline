package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.zykov.dao.ImageToProductDAO;
import ua.edu.sumdu.j2ee.zykov.model.ImageToProduct;

import java.util.List;

@Service
public class ImageToProductServiceImpl implements ImageToProductService {
    private final ImageToProductDAO imageToProductDAO;

    public ImageToProductServiceImpl(ImageToProductDAO imageToProductDAO) {
        this.imageToProductDAO = imageToProductDAO;
    }

    @Override
    public List<ImageToProduct> getImageToProductsAll() {
        return imageToProductDAO.findAll();
    }

    @Override
    public List<ImageToProduct> getImageToProductsByProductId(int productId) {
        return imageToProductDAO.findByProductId(productId);
    }

    @Override
    public ImageToProduct addImageToProduct(ImageToProduct imageToProduct) {
        return imageToProductDAO.save(imageToProduct);
    }

    @Override
    public ImageToProduct updateImageToProduct(int imageId, ImageToProduct imageToProduct) {
        return imageToProductDAO.update(imageToProduct);
    }

    @Override
    public ImageToProduct deleteImageToProduct(ImageToProduct imageToProduct) {
        return imageToProductDAO.delete(imageToProduct);
    }
}
