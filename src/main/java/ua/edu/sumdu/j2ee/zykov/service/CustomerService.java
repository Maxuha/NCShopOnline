package ua.edu.sumdu.j2ee.zykov.service;

import ua.edu.sumdu.j2ee.zykov.exception.CustomerNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.Customer;

import java.util.List;

public interface CustomerService {
    List<Customer> getCustomerAll();
    Customer getCustomerById(int id) throws CustomerNotExistException;
    Customer addCustomer(Customer customer);
    Customer updateCustomer(Customer customer);
    Customer deleteUCustomer(Customer customer);
}
