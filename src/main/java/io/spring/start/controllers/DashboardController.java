package io.spring.start.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import io.spring.start.dto.Login;

@Controller
@RequestMapping("dashboard")
public class DashboardController {
    @GetMapping
    public String index(Model model) { // untuk get all / select * / read
        model.addAttribute("dasboard", new Login());
        return "user-management/dashboard";
    }
}
// rifki