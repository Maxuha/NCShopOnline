package ua.edu.sumdu.j2ee.zykov.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ua.edu.sumdu.j2ee.zykov.dao.CustomerDAO;
import ua.edu.sumdu.j2ee.zykov.model.Customer;
import ua.edu.sumdu.j2ee.zykov.model.User;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

public class TestCustomerService {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerDAO customerDAO;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void when_save_customer_it_should_return_customer() {
        User user = new User(1, "maksym", "parolyaNet0");
        Customer customer = new Customer(user, "Maksym Zykov");
        customerService.addCustomer(customer);
        verify(customerDAO, times(1)).save(customer);
    }

    @Test
    public void get_all_customers() {
        User user1 = new User(1, "maksym", "parolyaNet0");
        User user2 = new User(2, "vasya", "parolyaNet1");
        User user3 = new User(3, "petya", "parolyaNet2");
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer(user1, "Maksym Zykov");
        Customer customer2 = new Customer(user2, "Vasya Ivanow");
        Customer customer3 = new Customer(user3, "Petya Ivanov");
        customers.add(customer1);
        customers.add(customer2);
        customers.add(customer3);
        when(customerDAO.findAll()).thenReturn(customers);
        List<Customer> customerAll = customerService.getCustomerAll();
        assertEquals(3, customerAll.size());
        verify(customerDAO, times(1)).findAll();
    }

    @Test
    public void get_by_id_customer() {
        User user = new User(1, "maksym", "parolyaNet0");
        when(customerDAO.findById(1)).thenReturn(new Customer(user, "Maksym Zykov"));
        Customer customer = customerService.getCustomerById(1);
        assertEquals(user, customer.getUser());
        assertEquals("Maksym Zykov", customer.getFullName());
    }

    @Test
    public void when_update_customer_it_should_return_customer() {
        User user = new User(1, "maksym", "parolyaNet0");
        Customer customer = new Customer(user, "Maksym Zykov");
        customerService.updateCustomer(customer);
        verify(customerDAO, times(1)).update(customer);
    }

    @Test
    public void when_delete_customer_it_should_return_customer() {
        User user = new User(1, "maksym", "parolyaNet0");
        Customer customer = new Customer(user, "Maksym Zykov");
        customerService.deleteUCustomer(customer);
        verify(customerDAO, times(1)).delete(customer);
    }
}
