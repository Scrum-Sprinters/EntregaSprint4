package com.ScrumSprinters.proyectoC3.Controladores;

import com.ScrumSprinters.proyectoC3.Entidades.Empleado;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class WebController {



    @GetMapping("/")
    public String showHome(@AuthenticationPrincipal User user) {

        System.out.println("usuario que hiz login" + user);
        return "/users";
//        return "home";
//        return "redirect:/login";
//        return "redirect:/users";
    }




    //TODO: si usuario ya está logeado debe redirigir a home
    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }
/*
    @PostMapping("/login")
    public String postLogin(){
    //public String postLogin(@ModelAttribute("formularioUsuario") Empleado empleado){
    //  System.out.println(empleado);
        return "redirect:/users";
    }
*/
    @GetMapping("/register")
    public String showRegister() {
        return "register";
    }
/*
    @GetMapping("/error")
    public String showError() {
        return "error";
    }
*/


}
