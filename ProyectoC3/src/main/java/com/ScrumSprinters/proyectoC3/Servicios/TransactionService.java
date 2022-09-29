package com.ScrumSprinters.proyectoC3.Servicios;

import com.ScrumSprinters.proyectoC3.Entidades.Empleado;
import com.ScrumSprinters.proyectoC3.Entidades.Empresa;
import com.ScrumSprinters.proyectoC3.Entidades.MovimientoDinero;
import com.ScrumSprinters.proyectoC3.Repositories.EmployeeRepository;
import com.ScrumSprinters.proyectoC3.Repositories.EnterpriseRepository;
import com.ScrumSprinters.proyectoC3.Repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository repository;
    @Autowired
    EnterpriseRepository enterpriseRepository;
    @Autowired
    EmployeeRepository empleadoRepository;

    public List<MovimientoDinero> getAllTransaction(Long id_empresa) throws Exception {

        Optional<Empresa> empresa = enterpriseRepository.findById(id_empresa);
        if (empresa.isEmpty())
            throw new Exception("empresa no existe");
        //se debe filtar por id_empresa agregar al repository el nuevo metodo
        return repository.findAll();
    }

    public List<MovimientoDinero> getAllTransaction() {
        return repository.findAll();
    }


    public void saveTransaction(MovimientoDinero movimientoDinero, Long id_empresa, Long id_empleado) throws Exception {
        Optional<Empresa> empresa = enterpriseRepository.findById(id_empresa);
        if (empresa.isEmpty())
            throw new Exception("Empresa no existe");
        movimientoDinero.setEmpresa(empresa.get());
        Optional<Empleado> empleado = empleadoRepository.findById(id_empleado);
        if (empleado.isEmpty())
            throw new Exception("Empleado no existe");

        movimientoDinero.setEmpleado(empleado.get());
        /*  esto debe ir en try catch por si no hay empresa con ese Id
        Empresa empresa = enterpriseRepository.findById(id_empresa).get();
        movimientoDinero.setEmpresa(empresa);
        */
        System.out.printf("Transaccion a guardar desde el service");
        System.out.println(movimientoDinero);
        repository.save(movimientoDinero);
    }

    //TODO: sin Terminar por falta de claridad en el endpoint /enterprise/{id]/movements/ ?????
    //TODO: no está claro si el id va a llegar en el body o en el path
    public void updateTransactionById(MovimientoDinero movimientoDinero) {
        //TODO: cambiar fecha de modificación de la entidad en la DB
        repository.save(movimientoDinero);
    }

    //TODO: sin Terminar por falta de claridad en el endpoint /enterprise/{id]/movements/ ?????
    //TODO: no está claro si el id va a llegar en el body o en el path
    public void deleteTransactionById(Long id) {
        repository.deleteById(id);
    }

}
