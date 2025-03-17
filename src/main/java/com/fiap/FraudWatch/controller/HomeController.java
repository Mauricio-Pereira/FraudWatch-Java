package com.fiap.FraudWatch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("mensagem", "Olá, Thymeleaf!");
        return "home"; // Procura um template home.html
    }
}
