package ua.edu.sumdu.j2ee.zykov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ua.edu.sumdu.j2ee.zykov.model.User;
import ua.edu.sumdu.j2ee.zykov.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class HomeRestController {
    private final UserService userService;

    public HomeRestController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public User getUser() {
        return userService.getUserByUserName(SecurityContextHolder.getContext().getAuthentication().getName());
    }
}
