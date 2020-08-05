package ua.edu.sumdu.j2ee.zykov.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.edu.sumdu.j2ee.zykov.dao.ProductHasOrderDAO;
import ua.edu.sumdu.j2ee.zykov.model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestProductHasOrder {
    @InjectMocks
    private ProductHasOrderServiceImpl productHasOrderService;

    @Mock
    private ProductHasOrderDAO productHasOrderDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    /*@Test
    public void when_save_product_has_order_it_should_return_product_has_order() {
        Image image = new Image(1, "image1.png");
        Category category = new Category("Smartphones", image);
        User user = new User(1, "maksym", "parolyaNet0");
        Shipper shipper = new Shipper(user, "NetCracker");
        Product product = new Product(1, "HP", "notebook", 10000, 0, category, shipper);
        Customer customer = new Customer(user, "Maksym Zykov");
        Order order = new Order(3, false, LocalDateTime.now(), customer);
        ProductHasOrder productHasOrder = new ProductHasOrder(product, order, 1);
        productHasOrderService.addProductHasOrder(productHasOrder);
        verify(productHasOrderDAO, times(1)).save(productHasOrder);
    }

    @Test
    public void get_all_product_has_orders() {
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
        Customer customer1 = new Customer(user1, "Maksym Zykov");
        Customer customer2 = new Customer(user2, "Vasya Ivanov");
        Customer customer3 = new Customer(user3, "Petya Ivanov");
        Order order1 = new Order(3, false, LocalDateTime.now(), customer1);
        Order order2 = new Order(4, true, LocalDateTime.now(), customer2);
        Order order3 = new Order(5, false, LocalDateTime.now(), customer3);
        ProductHasOrder productHasOrder1 = new ProductHasOrder(product1, order1, 1);
        ProductHasOrder productHasOrder2 = new ProductHasOrder(product2, order2, 2);
        ProductHasOrder productHasOrder3 = new ProductHasOrder(product3, order3, 1);
        List<ProductHasOrder> productHasOrders = new ArrayList<>();
        productHasOrders.add(productHasOrder1);
        productHasOrders.add(productHasOrder2);
        productHasOrders.add(productHasOrder3);
        when(productHasOrderDAO.findAll()).thenReturn(productHasOrders);
        List<ProductHasOrder> productsAll = productHasOrderService.getProductHasOrderAll();
        assertEquals(3, productsAll.size());
        verify(productHasOrderDAO, times(1)).findAll();
    }

    @Test
    public void get_by_product_product_has_order() {
        Category category = new Category(2, "Notebook", null, null);
        User user = new User(1, "maksym", "parolyaNet0");
        Shipper shipper = new Shipper(user, "NerCracker");
        Product product = new Product(1, "HP", "notebook", 10000, 0, category, shipper);
        Customer customer = new Customer(user, "Maksym Zykov");
        Order order = new Order(3, false, LocalDateTime.now(), customer);
        ProductHasOrder productHasOrder = new ProductHasOrder(product, order, 1);
        List<ProductHasOrder> productHasOrders = new ArrayList<>();
        productHasOrders.add(productHasOrder);
        when(productHasOrderDAO.findByProduct(1)).thenReturn(productHasOrders);
        List<ProductHasOrder> productHasOrderAll = productHasOrderService.getProductHasOrderByProduct(1);
        assertEquals(1, productHasOrderAll.size());
        assertEquals(product, productHasOrderAll.get(0).getProduct());
        assertEquals(order, productHasOrderAll.get(0).getOrder());
        assertEquals(1, productHasOrderAll.get(0).getCount());
    }

    @Test
    public void get_by_order_product_has_order() {
        Category category = new Category(2, "Notebook", null, null);
        User user = new User(1, "maksym", "parolyaNet0");
        Shipper shipper = new Shipper(user, "NerCracker");
        Product product = new Product(1, "HP", "notebook", 10000, 0, category, shipper);
        Customer customer = new Customer(user, "Maksym Zykov");
        Order order = new Order(3, false, LocalDateTime.now(), customer);
        ProductHasOrder productHasOrder = new ProductHasOrder(product, order, 1);
        List<ProductHasOrder> productHasOrders = new ArrayList<>();
        productHasOrders.add(productHasOrder);
        when(productHasOrderDAO.findByOrder(1)).thenReturn(productHasOrders);
        List<ProductHasOrder> productHasOrderAll = productHasOrderService.getProductHasOrderByOrder(1);
        assertEquals(1, productHasOrderAll.size());
        assertEquals(product, productHasOrderAll.get(0).getProduct());
        assertEquals(order, productHasOrderAll.get(0).getOrder());
        assertEquals(1, productHasOrderAll.get(0).getCount());
    }

    @Test
    public void when_update_product_has_order_it_should_return_product_has_order() {
        Category category = new Category(2, "Notebook", null, null);
        User user = new User(1, "maksym", "parolyaNet0");
        Shipper shipper = new Shipper(user, "NerCracker");
        Product product = new Product(1, "HP", "notebook", 10000, 0, category, shipper);
        Customer customer = new Customer(user, "Maksym Zykov");
        Order order = new Order(3, false, LocalDateTime.now(), customer);
        ProductHasOrder productHasOrder = new ProductHasOrder(product, order, 1);
        productHasOrderService.updateProductHasOrder(productHasOrder);
        verify(productHasOrderDAO, times(1)).update(productHasOrder);
    }

    @Test
    public void when_delete_product_has_order_it_should_return_product_has_order() {
        Category category = new Category(2, "Notebook", null, null);
        User user = new User(1, "maksym", "parolyaNet0");
        Shipper shipper = new Shipper(user, "NerCracker");
        Product product = new Product(1, "HP", "notebook", 10000, 0, category, shipper);
        Customer customer = new Customer(user, "Maksym Zykov");
        Order order = new Order(3, false, LocalDateTime.now(), customer);
        ProductHasOrder productHasOrder = new ProductHasOrder(product, order, 1);
        productHasOrderService.deleteProductHasOrder(productHasOrder);
        verify(productHasOrderDAO, times(1)).delete(productHasOrder);
    }*/
}
