package com.ScrumSprinters.proyectoC3.Controladores;

import com.ScrumSprinters.proyectoC3.Entidades.Empleado;
import com.ScrumSprinters.proyectoC3.Entidades.MovimientoDinero;
import com.ScrumSprinters.proyectoC3.Servicios.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class TransactionController {
    @Autowired
    TransactionService service;

    public TransactionController() {
    }

    @GetMapping("enterprises/{id}/movements")
    public String listaEmpresas(@PathVariable Long id, Model model) {

        //ir al servicio y traer la lista de transacciones de la empresa X
        //model.addAttribute(new Empleado());



        //TODO: revisar si es necesario enviar la empresa a la vista
        model.addAttribute("empresa_id", id);
        model.addAttribute("nuevoMovimiento", new MovimientoDinero());

        try {
            List<MovimientoDinero> listaMovimientos = service.getAllTransaction(id);
            Float totalMovimientos = 0f;
            for (MovimientoDinero movimiento : listaMovimientos)
                totalMovimientos += movimiento.getMonto();


            model.addAttribute("movements" , listaMovimientos);
            model.addAttribute("totalMovimientos", totalMovimientos);

        } catch (Exception e) {
            return "error";
        }

        //enviar al servicio  el ID de la empresa
        return "movements";
    }

    @PostMapping("/enterprises/{id}/movements")
    public String postNewTransaction(@PathVariable Long id, @ModelAttribute("nuevoMovimiento") MovimientoDinero nuevoMovimiento) {
        long empleado_id = 7; //TODO: eliminar este id hardcode

        try {
            service.saveTransaction(nuevoMovimiento, id, empleado_id);
        } catch (Exception e) {
            return "error";
        }

        return "redirect:/enterprises/{id}/movements";
    }

/*
    //TODO: Conectar con el servicio
    @GetMapping("/enterprises/{id}/movements")
    public String getTransactionById(@PathVariable Long id) {
        return "Se llama obtener movimientos para la empresa con Id: " + id.toString();
    }

    //TODO: Conectar con el servicio
    @PatchMapping("/enterprises/{id}/movements")
    public String updtadeTransaction(@PathVariable Long id) {
        return "Se llama modificar movimiento para la empresa con Id" + id.toString();
    }

    //TODO: Conectar con el servicio
    @DeleteMapping("/enterprises/{id}/movements")
    public String deleteTransaction(@PathVariable Long id) {
        return "Se llama borrar movimiento para la empresa con Id" + id.toString();
    }

  */

    //Implementacion futura con conexion a base de datos
    /*
    @PostMapping("/enterprises/{id}/movements")
    public String postNewTransaction(@PathVariable Long id_empresa ,  @RequestBody MovimientoDinero movimientoDinero){
        service.saveTransaction(id_empresa, movimientoDinero);
        return "Mensaje desde ruta /enterprises/[id]/movements con metodo POST esto inserta un nuevo Movimiento";
    }

    @GetMapping("/enterprises/{id}/movements")
    public List<MovimientoDinero> getTransactionById(@PathVariable Long id_empresa){
        return service.getAllTransaction(id_empresa);
    }
*/

}
