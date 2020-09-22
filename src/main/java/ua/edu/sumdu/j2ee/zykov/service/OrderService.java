package ua.edu.sumdu.j2ee.zykov.service;

import ua.edu.sumdu.j2ee.zykov.exception.OrderNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrderAll();
    Order getOrderById(int id) throws OrderNotExistException;
    Order getOrderByProcessedAndCustomerId(int customerId) throws OrderNotExistException;
    Order addOrder(Order order);
    Order updateOrder(Order order);
    Order deleteOrder(Order order);
}
