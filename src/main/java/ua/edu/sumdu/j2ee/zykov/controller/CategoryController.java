package ua.edu.sumdu.j2ee.zykov.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/categories")
public class CategoryController {
    @RequestMapping()
    public String showAllCategories() {
        return "categories";
    }
}
