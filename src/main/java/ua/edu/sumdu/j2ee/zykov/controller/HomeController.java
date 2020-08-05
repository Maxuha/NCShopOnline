package ua.edu.sumdu.j2ee.zykov.controller;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@CrossOrigin(origins = "http://localhost:3000")
public class HomeController {
    /*@RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String start() {
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public String index() {
        return "index";
    }*/

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
}
