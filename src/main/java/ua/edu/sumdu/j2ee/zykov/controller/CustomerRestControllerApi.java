package ua.edu.sumdu.j2ee.zykov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.model.Customer;
import ua.edu.sumdu.j2ee.zykov.service.CustomerService;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
public class CustomerRestControllerApi {
    private static final Logger logger = LoggerFactory.getLogger(CustomerRestControllerApi.class);
    private final CustomerService customerService;

    public CustomerRestControllerApi(CustomerService customerService) {
        this.customerService = customerService;
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        logger.info("Request to receive all customers");
        return customerService.getCustomerAll();
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Customer getByIdCustomer(@PathVariable int id) {
        logger.info("Request to receive customer by id {}", id);
        return customerService.getCustomerById(id);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Customer createCustomer(@RequestBody Customer customer) {
        logger.info("Request to create new customer {}", customer);
        return customerService.addCustomer(customer);
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Customer updateCustomer(@RequestBody Customer customer) {
        logger.info("Request to update customer {}", customer);
        return customerService.updateCustomer(customer);
    }

    @RequestMapping(value = "/remove", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public Customer removeUser(@RequestBody Customer customer) {
        logger.info("Request to delete customer {}", customer);
        return customerService.deleteUCustomer(customer);
    }
}
