package ua.edu.sumdu.j2ee.zykov.dao;

import ua.edu.sumdu.j2ee.zykov.exception.OrderNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.Order;

import java.util.List;

public interface OrderDAO {
    List<Order> findAll();
    Order findById(int id) throws OrderNotExistException;
    Order findByProcessedAndCustomerId(int customerId) throws OrderNotExistException;
    Order save(Order order);
    Order update(Order order);
    Order delete(Order order);
}
