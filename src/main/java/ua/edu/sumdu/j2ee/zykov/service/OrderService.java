package ua.edu.sumdu.j2ee.zykov.service;

import ua.edu.sumdu.j2ee.zykov.model.Order;

import java.util.List;

public interface OrderService {
    List<Order> getOrderAll();
    Order getOrderById(int id);
    Order getOrderByProcessedAndCustomerId(int customerId);
    Order addOrder(Order order);
    Order updateOrder(Order order);
    Order deleteOrder(Order order);
}
