package ua.edu.sumdu.j2ee.zykov.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.edu.sumdu.j2ee.zykov.dao.ImageDAO;
import ua.edu.sumdu.j2ee.zykov.model.Image;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestImageService {
    @InjectMocks
    private ImageServiceImpl imageService;

    @Mock
    private ImageDAO imageDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /*@Test
    public void when_save_image_it_should_return_image() {
        Image image = new Image(1, "image001.png");
        imageService.addImage(image);
        verify(imageDAO, times(1)).save(image);
    }

    @Test
    public void get_all_images() {
        List<Image> images = new ArrayList<>();
        Image image1 = new Image(1, "image001.png");
        Image image2 = new Image(2, "image002.png");
        Image image3 = new Image(3, "image003.png");
        images.add(image1);
        images.add(image2);
        images.add(image3);
        when(imageDAO.findAll()).thenReturn(images);
        List<Image> imageAll = imageService.getImageAll();
        assertEquals(3, imageAll.size());
        verify(imageDAO, times(1)).findAll();
    }

    @Test
    public void get_by_id_image() {
        when(imageDAO.findById(1)).thenReturn(new Image(1,  "image001.png"));
        Image image = imageService.getImageById(1);
        assertEquals(1, image.getId());
        assertEquals("image001.png", image.getImage());
    }

    @Test
    public void when_update_image_it_should_return_image() {
        Image image = new Image(1, "image001.png");
        imageService.updateImage(image);
        verify(imageDAO, times(1)).update(image);
    }

    @Test
    public void when_delete_image_it_should_return_image() {
        Image image = new Image(1, "image001.png");
        imageService.deleteImage(image);
        verify(imageDAO, times(1)).delete(image);
    }*/
}
