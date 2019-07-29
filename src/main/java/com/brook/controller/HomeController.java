package com.brook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {
    @RequestMapping(value = "/")
    public String index() {
        return "redirect:doc.html";
    }
}
