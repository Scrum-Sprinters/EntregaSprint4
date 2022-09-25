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
    EnterpriseService enterpriseService;  //para listar las empresas al crear el nuevo empleado


    public EmployeeController() {
    }

    @GetMapping("/users")
    public String getAllEmployees(Model model) {
        List<String> roles = new ArrayList<String>();
        EnumRole[] listaRoles = EnumRole.values();
        for (EnumRole rol : listaRoles) {
            roles.add(rol.toString());
        }

        model.addAttribute("empleados", service.getAllEmployees());
        model.addAttribute("empresas", enterpriseService.getAllEnterprise());
        model.addAttribute("nuevoEmpleado", new Empleado());
        model.addAttribute("listaRoles", roles);

        for (Empleado e : service.getAllEmployees()) {
            System.out.println(e.toString());
        }
        return "users";
    }

    @PostMapping("/users")
    public String saveEmployee(@ModelAttribute("nuevoEmpleado") Empleado empleado) {
//        model.addAttribute("empleados" ,  service.getAllEmployees());
        System.out.println("antes de guardar a base de datos");
        System.out.println(empleado);

        if (empleado.getId() == 0) {
            service.saveEmployee(empleado);
            System.out.println("despues de guardar a base de datos");
            System.out.println(empleado);
        }

        return "redirect:/users";
    }


    @PostMapping("/users/{id}")
    public String saveEmployeex(@ModelAttribute("empleado") Empleado empleado, @PathVariable Long id) {
//        model.addAttribute("empleados" ,  service.getAllEmployees());
        //TODO: modificar el metodo para que sea capaz de indetificar si es un nuevo usuario o uno ya existente mediante la fecha y enviando el id
        System.out.println("antes de guardar usuario editado /users/{id}");
        System.out.println(empleado);

        if (empleado.getId() == 0) {
            System.out.println("No tenia ID");
            empleado.setId(id);
            service.saveEmployee(empleado);
            System.out.println("despues de guardar a base de datos x");
            System.out.println(empleado);
        } else {
            System.out.println("ya tenia ID");
            service.saveEmployee(empleado);
            System.out.println("despues de guardar a base de datos x");
            System.out.println(empleado);
        }


        return "redirect:/users";
    }




/*
    @PostMapping("/users")
    public String updateEmployee(@ModelAttribute("")){
        return "redirect:/users";
    }
*/

    //TODO: cambiar a peticion tipo post por seguridad y enviar la accion mediante un formulario
    @GetMapping("/users/{id}/delete")
    public String deleteEmployee(@PathVariable Long id) {
        service.deleteEmployeeById(id);
        return "redirect:/users";
    }


    @GetMapping("/users/{id}")
    public String editEmployee(@PathVariable Long id, Model model) {
        EnumRole[] listaRoles = EnumRole.values();

        //TODO: incluir un try catch por si no viene el empleado
        Empleado empleado = service.getEmployeeById(id);
        System.out.println("se encontro el siguiente empleado");
        System.out.println(empleado);
        model.addAttribute("empleado", empleado);
//        model.addAttribute("listaRoles" , roles);
        model.addAttribute("listaRoles", listaRoles);
        model.addAttribute("empresas", enterpriseService.getAllEnterprise());
        return "usersEdit";
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
