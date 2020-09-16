package ua.edu.sumdu.j2ee.zykov.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.model.Customer;
import ua.edu.sumdu.j2ee.zykov.model.Role;
import ua.edu.sumdu.j2ee.zykov.model.Shipper;
import ua.edu.sumdu.j2ee.zykov.model.User;
import ua.edu.sumdu.j2ee.zykov.service.CustomerService;
import ua.edu.sumdu.j2ee.zykov.service.ShipperService;
import ua.edu.sumdu.j2ee.zykov.service.UserHasRoleService;
import ua.edu.sumdu.j2ee.zykov.service.UserService;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {
    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
    private final UserService userService;
    private final CustomerService customerService;
    private final ShipperService shipperService;
    private final UserHasRoleService userHasRoleService;

    public HomeController(UserService userService, CustomerService customerService, ShipperService shipperService, UserHasRoleService userHasRoleService) {
        this.userService = userService;
        this.customerService = customerService;
        this.shipperService = shipperService;
        this.userHasRoleService = userHasRoleService;
    }

    @RequestMapping(value = "/403")
    @ResponseStatus(HttpStatus.OK)
    public String error403() {
        logger.info("Open error page");
        return "403";
    }

    @RequestMapping(value = "/logout")
    @ResponseStatus(HttpStatus.OK)
    public String logout() {
        logger.info("Open logout page");
        return "logout";
    }

    @RequestMapping(value = "/register/customer")
    @ResponseStatus(HttpStatus.OK)
    public String registerCustomer(@RequestBody User user, @RequestHeader String info) {
        logger.info("Request to register new customer {}", user);
        user = userService.addUser(user);
        userHasRoleService.addUserHasRole(user, Role.ROLE_CUSTOMER);
        Customer customer = new Customer(user, info);
        customerService.addCustomer(customer);
        logger.info("Register success! Redirect to home page");
        return "redirect:/index.html";
    }

    @RequestMapping(value = "/register/shipper")
    @ResponseStatus(HttpStatus.OK)
    public String registerShipper(@RequestBody User user, @RequestHeader String info) {
        logger.info("Request to register new shipper {}", user);
        user = userService.addUser(user);
        userHasRoleService.addUserHasRole(user, Role.ROLE_SHIPPER);
        Shipper shipper = new Shipper(user, info);
        shipperService.addShipper(shipper);
        logger.info("Register success! Redirect to home page");
        return "redirect:/index.html";
    }
}
