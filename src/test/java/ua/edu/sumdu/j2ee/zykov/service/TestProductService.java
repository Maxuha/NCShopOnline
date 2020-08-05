package ua.edu.sumdu.j2ee.zykov.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.edu.sumdu.j2ee.zykov.dao.CategoryDAO;
import ua.edu.sumdu.j2ee.zykov.dao.ProductDAO;
import ua.edu.sumdu.j2ee.zykov.model.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestProductService {
    @InjectMocks
    private ProductServiceImpl productService;

    @Mock
    private ProductDAO productDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

   /* @Test
    public void when_save_product_it_should_return_product() {
        Image image = new Image(1, "image1.png");
        Category category = new Category("Smartphones", image);
        User user = new User(1, "maksym", "parolyaNet0");
        Shipper shipper = new Shipper(user, "NetCracker");
        Product product = new Product(1, "HP", "notebook", 10000, 0, category, shipper);
        productService.addProduct(product);
        verify(productDAO, times(1)).save(product);
    }

    @Test
    public void get_all_products() {
        Image image = new Image("image001.png");
        image.setId(1);
        Image image1 = new Image("image002.png");
        image1.setId(2);
        Category category1 = new Category("All", null, null);
        Category category2 = new Category("Notebook", null, category1);
        Category category3 = new Category("Computer", image, category1);
        Category category4 = new Category("Ware", image1, null);
        Category category6 = new Category("Software", image1, category4);
        User user1 = new User(1, "maksym", "parolyaNet0");
        User user2 = new User(2, "vasya", "parolyaNet1");
        User user3 = new User(3, "petya", "parolyaNet2");
        Shipper shipper1 = new Shipper(user1, "NetCracker");
        Shipper shipper2 = new Shipper(user2, "McDonalds");
        Shipper shipper3 = new Shipper(user3, "Mercedes");
        Product product1 = new Product(1, "HP", "notebook", 10000, 0, category2, shipper1);
        Product product2 = new Product(2, "Acer", "pc", 15000, 150, category3, shipper2);
        Product product3 = new Product(3, "Lenovo", "notebook", 16000, 0, category6, shipper3);
        List<Product> products = new ArrayList<>();
        products.add(product1);
        products.add(product2);
        products.add(product3);
        when(productDAO.findAll()).thenReturn(products);
        List<Product> productsAll = productService.getProductAll();
        assertEquals(3, productsAll.size());
        verify(productDAO, times(1)).findAll();
    }

    @Test
    public void get_by_id_product() {
        Category category = new Category(2, "Notebook", null, null);
        User user = new User(1, "maksym", "parolyaNet0");
        Shipper shipper = new Shipper(user, "NerCracker");
        when(productDAO.findById(1)).thenReturn(new Product(1, "HP", "notebook", 10000, 0, category, shipper));
        Product product = productService.getProductById(1);
        assertEquals(1, product.getId());
        assertEquals("HP", product.getTitle());
        assertEquals("notebook", product.getDescription());
        assertEquals(10000, product.getPrice());
        assertEquals(0, product.getDiscount());
        assertEquals(category, product.getCategory());
        assertEquals(shipper, product.getShipper());
    }

    @Test
    public void when_update_product_it_should_return_product() {
        Category category = new Category(2, "Notebook", null, null);
        User user = new User(1, "maksym", "parolyaNet0");
        Shipper shipper = new Shipper(user, "NerCracker");
        Product product = new Product(1, "HP", "notebook", 10000, 0, category, shipper);
        productService.updateProduct(product);
        verify(productDAO, times(1)).update(product);
    }

    @Test
    public void when_delete_product_it_should_return_product() {
        Category category = new Category(2, "Notebook", null, null);
        User user = new User(1, "maksym", "parolyaNet0");
        Shipper shipper = new Shipper(user, "NerCracker");
        Product product = new Product(1, "HP", "notebook", 10000, 0, category, shipper);
        productService.deleteProduct(product);
        verify(productDAO, times(1)).delete(product);
    }*/
}
