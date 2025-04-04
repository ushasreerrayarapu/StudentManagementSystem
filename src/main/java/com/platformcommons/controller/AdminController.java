package com.platformcommons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/adminLogin")
    public String loginPage() {
        return "adminLogin";
    }
}

