package ua.edu.sumdu.j2ee.zykov.dao;

import ua.edu.sumdu.j2ee.zykov.exception.ImageNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.Image;

import java.util.List;

public interface ImageDAO {
    List<Image> findAll();
    Image findById(int id) throws ImageNotExistException;
    Image save(Image image);
    Image update(Image image);
    Image delete(Image image);
}
