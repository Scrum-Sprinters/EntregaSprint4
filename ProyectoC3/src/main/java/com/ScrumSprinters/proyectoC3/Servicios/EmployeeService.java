package com.ScrumSprinters.proyectoC3.Servicios;

import com.ScrumSprinters.proyectoC3.Entidades.Empleado;
import com.ScrumSprinters.proyectoC3.Entidades.Empresa;
import com.ScrumSprinters.proyectoC3.Repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository repository;

    public EmployeeService() {
    }

    public void saveEmployee(Empleado empleado) { //post /users
        // si no viene el objeto empresa pero si el id de la empresa se crea una empresa solo con id y se guarda en base de datos

        empleado.setActivo(true);
        if (empleado.getEmpresa() == null)
            if(empleado.getEmpresa_id() != null)
                empleado.setEmpresa(new Empresa(empleado.getEmpresa_id()));

        repository.save(empleado);
    }

    public List<Empleado> getAllEmployees() { //metodo get /users
        return repository.findAll();
    }

    public Empleado getEmployeeById(Long id) { //metodo get /users/{id}
        var empleado = repository.findById(id);
        return empleado.get();
    }

    public void updateEmployeeById(Empleado empleado) { //metodo patch /enterprises/{id}
        repository.save(empleado);
    }

    public void deleteEmployeeById(Long id) {
        repository.deleteById(id);

    }
}
