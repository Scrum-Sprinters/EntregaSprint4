package com.ScrumSprinters.proyectoC3.Entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import net.bytebuddy.asm.Advice;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Enterprise")
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", unique = true)
    private String nombre;
    @Column(name = "document", unique = true)
    private Long nit;
    @Column(name = "phone")
    private String telefono;
    @Column(name = "address")
    private String direccion;
    @JsonIgnore
    @OneToMany(mappedBy = "empresa")
    private List<Empleado> empleados;
    @JsonIgnore
    @OneToMany(mappedBy = "empresa")
    private List<MovimientoDinero> movimientoDineros;
    @CreationTimestamp
    @Column(name = "created_at")
    private Date creado;
    @UpdateTimestamp
    @Column(name = "updated_at")
    private Date modificado;


    @Column(name = "activo")
    private Boolean activo;


    //Constructor

    public Empresa(Long id, Long nit, String nombre, String direccion, String telefono, Date creado, Date modificado) {
        this.id = id;
        this.nit = nit;
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
        this.creado = creado;
        this.modificado = modificado;
    }

    public Empresa() {

    }

    public Empresa(Long id){
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public Long getNit() {
        return nit;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public Date getCreado() {
        return creado;
    }

    public void setCreado(Date creado) {
        this.creado = creado;
    }

    public Date getModificado() {
        return modificado;
    }

    public void setModificado(Date modificado) {
        this.modificado = modificado;
    }

    public List<Empleado> getEmpleados() {
        return empleados;
    }

    public List<MovimientoDinero> getMovimientoDineros() {
        return movimientoDineros;
    }

    public void setEmpleados(List<Empleado> empleados) {
        this.empleados = empleados;
    }

    public void setMovimientoDineros(List<MovimientoDinero> movimientoDineros) {
        this.movimientoDineros = movimientoDineros;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    @Override
    public String toString() {
        return "Empresa{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", nit=" + nit +
                ", telefono='" + telefono + '\'' +
                ", direccion='" + direccion + '\'' +
                ", creado=" + creado +
                ", modificado=" + modificado +
                ", activo=" + activo +
                '}';
    }
}
