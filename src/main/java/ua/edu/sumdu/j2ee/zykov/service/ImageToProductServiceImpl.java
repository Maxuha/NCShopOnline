package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.zykov.dao.ImageToProductDAO;
import ua.edu.sumdu.j2ee.zykov.model.Image;
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
        Image image = imageToProduct.getImage() != null ? imageToProduct.getImage() : new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSBWCN9Acq8fXUM4G4e3c9l--1RWCkVX9folw&usqp=CAU");
        imageToProduct.setImage(image);
        return imageToProductDAO.save(imageToProduct);
    }

    @Override
    public ImageToProduct updateImageToProduct(int imageId, ImageToProduct imageToProduct) {
        Image image = imageToProduct.getImage() != null ? imageToProduct.getImage() : new Image("https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSBWCN9Acq8fXUM4G4e3c9l--1RWCkVX9folw&usqp=CAU");
        imageToProduct.setImage(image);
        return imageToProductDAO.update(imageId, imageToProduct);
    }

    @Override
    public ImageToProduct deleteImageToProduct(ImageToProduct imageToProduct) {
        return imageToProductDAO.delete(imageToProduct);
    }
}
