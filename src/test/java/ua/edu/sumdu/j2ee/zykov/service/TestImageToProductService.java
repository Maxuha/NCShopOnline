package ua.edu.sumdu.j2ee.zykov.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.edu.sumdu.j2ee.zykov.dao.ImageToProductDAO;
import ua.edu.sumdu.j2ee.zykov.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestImageToProductService {
    @InjectMocks
    private ImageToProductServiceImpl imageToProductService;

    @Mock
    private ImageToProductDAO imageToProductDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /*@Test
    public void when_save_image_to_product_has_order_it_should_return_image_to_product_has_order() {
        Image image = new Image(1, "image1.png");
        Category category = new Category("Smartphones", image);
        User user = new User(1, "maksym", "parolyaNet0");
        Shipper shipper = new Shipper(user, "NetCracker");
        Product product = new Product(1, "HP", "notebook", 10000, 0, category, shipper);
        ImageToProduct imageToProduct = new ImageToProduct(product, image);
        imageToProductService.addImageToProduct(imageToProduct);
        verify(imageToProductDAO, times(1)).save(imageToProduct);
    }

    @Test
    public void get_all_image_to_product() {
        Image image1 = new Image(1, "image001.png");
        Image image2 = new Image(2, "image002.png");
        Image image3 = new Image(2, "image003.png");
        Category category1 = new Category("All", null, null);
        Category category2 = new Category("Notebook", null, category1);
        Category category3 = new Category("Computer", image1, category1);
        Category category4 = new Category("Ware", image2, null);
        Category category6 = new Category("Software", image2, category4);
        User user1 = new User(1, "maksym", "parolyaNet0");
        User user2 = new User(2, "vasya", "parolyaNet1");
        User user3 = new User(3, "petya", "parolyaNet2");
        Shipper shipper1 = new Shipper(user1, "NetCracker");
        Shipper shipper2 = new Shipper(user2, "McDonalds");
        Shipper shipper3 = new Shipper(user3, "Mercedes");
        Product product1 = new Product(1, "HP", "notebook", 10000, 0, category2, shipper1);
        Product product2 = new Product(2, "Acer", "pc", 15000, 150, category3, shipper2);
        Product product3 = new Product(3, "Lenovo", "notebook", 16000, 0, category6, shipper3);
        ImageToProduct imageToProduct1 = new ImageToProduct(product1, image1);
        ImageToProduct imageToProduct2 = new ImageToProduct(product2, image2);
        ImageToProduct imageToProduct3 = new ImageToProduct(product3, image3);
        List<ImageToProduct> imageToProducts = new ArrayList<>();
        imageToProducts.add(imageToProduct1);
        imageToProducts.add(imageToProduct2);
        imageToProducts.add(imageToProduct3);
        when(imageToProductDAO.findAll()).thenReturn(imageToProducts);
        List<ImageToProduct> imageToProductsAll = imageToProductService.getImageToProductsAll();
        assertEquals(3, imageToProducts.size());
        verify(imageToProductDAO, times(1)).findAll();
    }

    @Test
    public void get_by_product_id_image_to_product() {
        Image image1 = new Image(1, "image001.png");
        Category category1 = new Category("All", null, null);
        Category category2 = new Category("Notebook", null, category1);
        User user1 = new User(1, "maksym", "parolyaNet0");
        Shipper shipper1 = new Shipper(user1, "NetCracker");
        Product product1 = new Product(1, "HP", "notebook", 10000, 0, category2, shipper1);
        ImageToProduct imageToProduct1 = new ImageToProduct(product1, image1);
        List<ImageToProduct> imageToProducts = new ArrayList<>();
        imageToProducts.add(imageToProduct1);
        when(imageToProductDAO.findByProductId(1)).thenReturn(imageToProducts);
        List<ImageToProduct> imageToProductsAll = imageToProductService.getImageToProductsByProductId(1);
        assertEquals(1, imageToProductsAll.size());
        assertEquals(product1, imageToProductsAll.get(0).getProduct());
        assertEquals(image1, imageToProductsAll.get(0).getImage());
        verify(imageToProductDAO, times(1)).findByProductId(1);
    }

    @Test
    public void when_update_image_to_product_it_should_return_image_to_product() {
        Image image1 = new Image(1, "image002.png");
        Category category1 = new Category("All", null, null);
        Category category2 = new Category("Notebook", null, category1);
        User user1 = new User(1, "maksym", "parolyaNet0");
        Shipper shipper1 = new Shipper(user1, "NetCracker");
        Product product1 = new Product(1, "HP", "notebook", 10000, 0, category2, shipper1);
        ImageToProduct imageToProduct1 = new ImageToProduct(product1, image1);
        imageToProductService.updateImageToProduct(1, imageToProduct1);
        verify(imageToProductDAO, times(1)).update(1, imageToProduct1);
    }

    @Test
    public void when_delete_image_to_product_it_should_return_image_to_product() {
        Image image1 = new Image(1, "image001.png");
        Category category1 = new Category("All", null, null);
        Category category2 = new Category("Notebook", null, category1);
        User user1 = new User(1, "maksym", "parolyaNet0");
        Shipper shipper1 = new Shipper(user1, "NetCracker");
        Product product1 = new Product(1, "HP", "notebook", 10000, 0, category2, shipper1);
        ImageToProduct imageToProduct1 = new ImageToProduct(product1, image1);
        imageToProductService.deleteImageToProduct(imageToProduct1);
        verify(imageToProductDAO, times(1)).delete(imageToProduct1);
    }*/
}
