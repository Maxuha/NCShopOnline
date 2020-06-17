package ua.edu.sumdu.j2ee.zykov.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.edu.sumdu.j2ee.zykov.dao.CustomerDAO;
import ua.edu.sumdu.j2ee.zykov.dao.OrderDAO;
import ua.edu.sumdu.j2ee.zykov.model.Customer;
import ua.edu.sumdu.j2ee.zykov.model.Order;
import ua.edu.sumdu.j2ee.zykov.model.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestOrderService {
    @InjectMocks
    private OrderServiceImpl orderService;

    @Mock
    private OrderDAO orderDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_save_order_it_should_return_order() {
        User user = new User(1, "maksym", "parolyaNet0");
        Customer customer = new Customer(user, "Maksym Zykov");
        Order order = new Order(1, false, LocalDateTime.now(), customer);
        orderService.addOrder(order);
        verify(orderDAO, times(1)).save(order);
    }

    @Test
    public void get_all_orders() {
        User user1 = new User(1, "maksym", "parolyaNet0");
        User user2 = new User(2, "vasya", "parolyaNet1");
        User user3 = new User(3, "petya", "parolyaNet2");

        Customer customer1 = new Customer(user1, "Maksym Zykov");
        Customer customer2 = new Customer(user2, "Vasya Ivanow");
        Customer customer3 = new Customer(user3, "Petya Ivanov");

        Order order1 = new Order(1, false, LocalDateTime.now(), customer1);
        Order order2 = new Order(2, true, LocalDateTime.now(), customer2);
        Order order3 = new Order(3, false, LocalDateTime.now(), customer3);

        List<Order> orders = new ArrayList<>();
        orders.add(order1);
        orders.add(order2);
        orders.add(order3);

        when(orderDAO.findAll()).thenReturn(orders);
        List<Order> orderAll = orderService.getOrderAll();
        assertEquals(3, orderAll.size());
        verify(orderDAO, times(1)).findAll();
    }

    @Test
    public void get_by_id_order() {
        User user = new User(1, "maksym", "parolyaNet0");
        Customer customer = new Customer(user, "Maksym Zykov");
        LocalDateTime dateTime = LocalDateTime.now();
        when(orderDAO.findById(1)).thenReturn(new Order(1, false, dateTime, customer));
        Order order = orderService.getOrderById(1);
        assertEquals(1, order.getId());
        assertFalse(order.isProcessed());
        assertEquals(dateTime, order.getDate());
        assertEquals(customer, order.getCustomer());
    }

    @Test
    public void when_update_order_it_should_return_oder() {
        User user = new User(1, "maksym", "parolyaNet0");
        Customer customer = new Customer(user, "Maksym Zykov");
        Order order = new Order(1, false, LocalDateTime.now(), customer);
        orderService.updateOrder(order);
        verify(orderDAO, times(1)).update(order);
    }

    @Test
    public void when_delete_order_it_should_return_order() {
        User user = new User(1, "maksym", "parolyaNet0");
        Customer customer = new Customer(user, "Maksym Zykov");
        Order order = new Order(1, false, LocalDateTime.now(), customer);
        orderService.deleteOrder(order);
        verify(orderDAO, times(1)).delete(order);
    }
}
