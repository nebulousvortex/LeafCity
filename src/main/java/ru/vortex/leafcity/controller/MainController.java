package ru.vortex.leafcity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/Hello")
    public String index() {
        return "physics";
    }
}
