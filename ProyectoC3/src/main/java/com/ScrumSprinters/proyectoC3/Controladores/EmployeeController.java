package com.ScrumSprinters.proyectoC3.Controladores;

import com.ScrumSprinters.proyectoC3.Entidades.Empleado;
import com.ScrumSprinters.proyectoC3.Servicios.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService service;

    public EmployeeController(){
    }

    @GetMapping("/users")
    public String getAllEmployees(Model model){
        model.addAttribute("empleados" ,  service.getAllEmployees());
        return "users";
    }

    @PostMapping("/users")
    public String saveEmployee(Model model){
        model.addAttribute("empleados" ,  service.getAllEmployees());
        model.addAttribute("nuevoEmpleado" , new Empleado());
        return "users";
    }



/*
    @PostMapping("/users")
    public String saveEmployee(@RequestBody Empleado empleado){
            service.saveEmployee(empleado);
            return "Se ingresa el nuevo usario: " + empleado.toString();
    }

    @GetMapping("/users/{id}")
    public Empleado getEmployeeById(@PathVariable Long id){
            return service.getEmployeeById(id);
    }

    @PatchMapping("/users/{id}")
    public String updateEmployeeById(@PathVariable Long id, @RequestBody Empleado empleado){
        empleado.setId(id);
        service.updateEmployeeById(empleado);
        return "Se llama modificar empleado con id: " + id;
    }

    @DeleteMapping("users/{id}")
    public String deleteEmployeeById(@PathVariable Long id){
        service.deleteEmployeeById(id);
        return "Se llama borrar empleado con id: " + id;
    }

*/
}
