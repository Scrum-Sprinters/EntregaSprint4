package com.ScrumSprinters.proyectoC3.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {



    @GetMapping("/")
    public String showHome() {
//        return "home";
        return "redirect:/login";
    }
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }

    @GetMapping("/error")
    public String showError() {
        return "error";
    }


}
