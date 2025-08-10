package com.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String index() {
        return "index"; // landing page template
    }

    @GetMapping("/thankyou")
    public String thankyou() {
        return "thankyou"; // create thankyou.html template later
    }
}
