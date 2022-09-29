package com.ScrumSprinters.proyectoC3.Repositories;

import com.ScrumSprinters.proyectoC3.Entidades.Empleado;
import com.ScrumSprinters.proyectoC3.Entidades.MovimientoDinero;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<MovimientoDinero, Long> {

    @Query(value = "select * from \"transaction\" WHERE enterprise_id = 21" , nativeQuery = true)
    public List<Empleado> findAllMovementsByEnterpriseId(Long id);
}
