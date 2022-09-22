package com.ScrumSprinters.proyectoC3.Controladores;

import com.ScrumSprinters.proyectoC3.Entidades.Empleado;
import com.ScrumSprinters.proyectoC3.Entidades.EnumRole;
import com.ScrumSprinters.proyectoC3.Servicios.EmployeeService;
import com.ScrumSprinters.proyectoC3.Servicios.EnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeService service;
    @Autowired
    EnterpriseService enterpriseService;



    public EmployeeController() {
    }

    @GetMapping("/users")
    public String getAllEmployees(Model model) {
        List<String> roles = new ArrayList<String>();
        EnumRole[] listaRoles = EnumRole.values();
        for (EnumRole rol:listaRoles) {
            roles.add(rol.toString());
        }

        model.addAttribute("empleados", service.getAllEmployees());
        model.addAttribute("empresas", enterpriseService.getAllEnterprise());
        model.addAttribute("nuevoEmpleado", new Empleado());
        model.addAttribute("listaRoles" , roles);

        for(Empleado e : service.getAllEmployees()) {
            System.out.println(e.toString());
        }
        return "users";
    }

    @PostMapping("/users")
    public String saveEmployee(@ModelAttribute("nuevoEmpleado") Empleado empleado) {
//        model.addAttribute("empleados" ,  service.getAllEmployees());
        System.out.println("antes de guardar a base de datos");
        System.out.println(empleado);
        service.saveEmployee(empleado);
        System.out.println("despues de guardar a base de datos");
        System.out.println(empleado);
        return "redirect:/users";
    }

    @GetMapping("/users/{id}/delete")
    public String deleteEmployee(@PathVariable Long id){
        service.deleteEmployeeById(id);
        return "redirect:/users";
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
