package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.zykov.dao.ImageDAO;
import ua.edu.sumdu.j2ee.zykov.model.Image;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {
    private final ImageDAO imageDAO;

    public ImageServiceImpl(ImageDAO imageDAO) {
        this.imageDAO = imageDAO;
    }

    @Override
    public List<Image> getImageAll() {
        return imageDAO.findAll();
    }

    @Override
    public Image getImageById(int id) {
        return imageDAO.findById(id);
    }

    @Override
    public Image addImage(Image image) {
        return imageDAO.save(image);
    }

    @Override
    public Image updateImage(Image image) {
        return imageDAO.update(image);
    }

    @Override
    public Image deleteImage(Image image) {
        return imageDAO.delete(image);
    }
}
