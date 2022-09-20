package com.ScrumSprinters.proyectoC3.Controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class WebController {


    @GetMapping("/empleados")
    public String mostrarEmpleados() {
        return "index2";
    }
}
