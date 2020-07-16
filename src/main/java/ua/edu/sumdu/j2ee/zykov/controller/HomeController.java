package ua.edu.sumdu.j2ee.zykov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.edu.sumdu.j2ee.zykov.model.Role;
import ua.edu.sumdu.j2ee.zykov.model.User;
import ua.edu.sumdu.j2ee.zykov.service.UserHasRoleService;
import ua.edu.sumdu.j2ee.zykov.service.UserService;

@Controller
public class HomeController {
    private final UserService userService;
    private final UserHasRoleService userHasRoleService;

    public HomeController(UserService userService, UserHasRoleService userHasRoleService) {
        this.userService = userService;
        this.userHasRoleService = userHasRoleService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String start() {
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/main", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String main() {
        return "main";
    }

    @RequestMapping(value = "/login")
    @ResponseStatus(HttpStatus.OK)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/403")
    @ResponseStatus(HttpStatus.OK)
    public String error403() {
        return "403";
    }

    @RequestMapping(value = "/logout")
    @ResponseStatus(HttpStatus.OK)
    public String logout() {
        return "logout";
    }

    @RequestMapping(value = "shipper/register", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String registerPage() {
        return "register";
    }

    @RequestMapping(value = "shipper/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String shipperRegister(User user) {
        User saveUser = userService.addUser(user);
        userHasRoleService.addUserHasRole(saveUser, Role.ROLE_SHIPPER);
        return "redirect:/";
    }

    @RequestMapping(value = "customer/register", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public String customerRegister(User user) {
        User saveUser = userService.addUser(user);
        userHasRoleService.addUserHasRole(saveUser, Role.ROLE_CUSTOMER);
        return "redirect:/";
    }
}
