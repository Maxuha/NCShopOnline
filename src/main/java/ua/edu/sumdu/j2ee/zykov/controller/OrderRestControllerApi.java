package ua.edu.sumdu.j2ee.zykov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.model.Order;
import ua.edu.sumdu.j2ee.zykov.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/api/order")
@CrossOrigin(origins = "http://localhost:3000")
public class OrderRestControllerApi {
    private static final Logger logger = LoggerFactory.getLogger(OrderRestControllerApi.class);
    private final OrderService orderService;

    public OrderRestControllerApi(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Order> getAllOrders() {
        logger.info("Request to receive all orders");
        return orderService.getOrderAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Order getByIdOrder(@PathVariable int id) {
        logger.info("Request to receive order by {}", id);
        return orderService.getOrderById(id);
    }

    @RequestMapping(value = "/get/processed", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Order getOrderByProcessedAndCustomerId(@RequestParam int customerId) {
        logger.info("Request to receive order for customer {}", customerId);
        return orderService.getOrderByProcessedAndCustomerId(customerId);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Order createOrder(@RequestBody Order order) {
        logger.info("Request to create new order {}", order);
        return orderService.addOrder(order);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Order updateOrder(@RequestBody Order order) {
        logger.info("Request to update order {}", order);
        return orderService.updateOrder(order);
    }

    @RequestMapping(value = "remove", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Order removeOrder(@RequestBody Order order) {
        logger.info("Request to remove order {}", order);
        return orderService.deleteOrder(order);
    }
}
