package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.zykov.dao.CustomerDAO;
import ua.edu.sumdu.j2ee.zykov.model.Customer;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerDAO customerDAO;

    public CustomerServiceImpl(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @Override
    public List<Customer> getCustomerAll() {
        return customerDAO.findAll();
    }

    @Override
    public Customer getCustomerById(int id) {
        return customerDAO.findById(id);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return customerDAO.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        return customerDAO.update(customer);
    }

    @Override
    public Customer deleteUCustomer(Customer customer) {
        return customerDAO.delete(customer);
    }
}
