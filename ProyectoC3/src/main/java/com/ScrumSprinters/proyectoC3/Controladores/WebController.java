package com.ScrumSprinters.proyectoC3.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {



    @GetMapping("/")
    public String showHome() {
//        return "home";
//        return "redirect:/login";
        return "redirect:/users";
    }

    //TODO: si usuario ya está logeado debe redirigir a home
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
