package ua.edu.sumdu.j2ee.zykov.service;

import ua.edu.sumdu.j2ee.zykov.model.Image;

import java.util.List;

public interface ImageService {
    List<Image> getImageAll();
    Image getImageById(int id);
    Image addImage(Image image);
    Image updateImage(Image image);
    Image deleteImage(Image image);
}
