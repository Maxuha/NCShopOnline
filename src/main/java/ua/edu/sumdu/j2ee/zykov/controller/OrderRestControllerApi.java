package ua.edu.sumdu.j2ee.zykov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.model.Order;
import ua.edu.sumdu.j2ee.zykov.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderRestControllerApi {
    private final OrderService orderService;

    public OrderRestControllerApi(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders() {
        return orderService.getOrderAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Order getByIdOrder(@PathVariable int id) {
        return orderService.getOrderById(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Order createOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Order updateOrder(@RequestBody Order order) {
        return orderService.updateOrder(order);
    }

    @RequestMapping(value = "remove", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Order removeOrder(@RequestBody Order order) {
        return orderService.deleteOrder(order);
    }
}
