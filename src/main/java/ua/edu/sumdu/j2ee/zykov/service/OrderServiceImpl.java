package ua.edu.sumdu.j2ee.zykov.service;

import org.springframework.stereotype.Service;
import ua.edu.sumdu.j2ee.zykov.dao.OrderDAO;
import ua.edu.sumdu.j2ee.zykov.exception.OrderNotExistException;
import ua.edu.sumdu.j2ee.zykov.model.Order;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDAO orderDAO;

    public OrderServiceImpl(OrderDAO orderDAO) {
        this.orderDAO = orderDAO;
    }

    @Override
    public List<Order> getOrderAll() {
        return orderDAO.findAll();
    }

    @Override
    public Order getOrderById(int id) throws OrderNotExistException {
        return orderDAO.findById(id);
    }

    @Override
    public Order getOrderByProcessedAndCustomerId(int customerId) throws OrderNotExistException {
        return orderDAO.findByProcessedAndCustomerId(customerId);
    }

    @Override
    public Order addOrder(Order order) {
        return orderDAO.save(order);
    }

    @Override
    public Order updateOrder(Order order) {
        return orderDAO.update(order);
    }

    @Override
    public Order deleteOrder(Order order) {
        return orderDAO.delete(order);
    }
}
