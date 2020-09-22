package ua.edu.sumdu.j2ee.zykov.dao;

import ua.edu.sumdu.j2ee.zykov.exception.CustomerNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.Customer;

import java.util.List;

public interface CustomerDAO {
    List<Customer> findAll();
    Customer findById(int id) throws CustomerNotExistException;
    Customer save(Customer customer);
    Customer update(Customer customer);
    Customer delete(Customer customer);
}
