/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Usuarios;

import Entidades.Proyectos.Actividad;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
@DiscriminatorValue("ALUMNO")
public class Alumno extends Usuario implements Serializable {
    
    @Column(name = "Carrera", nullable = false, length = 150)
    private String Carrera;
    
    @Column(name = "Area_de_interes", nullable = true, length = 100)
    private String Area_de_interes;
    
    @Column(name = "Disponibilidad", nullable = true, length = 150)
    private String Disponibilidad;

    @ManyToMany
    @JoinTable(name = "jnd_Alumno_Actividad", joinColumns = @JoinColumn(name = "Actividad_fk"), inverseJoinColumns = @JoinColumn(name = "Alumno_fk"))
    private List<Actividad> actividades;
    
    @ManyToMany(mappedBy="peticionarios")
    private List<Actividad> peticionesActividades;
    
    public Alumno() {}

    public Alumno (String nombre, String apellidos, String telefono, String direccion, String correo,String contrasenia, String Carrera)
    {
        super(nombre, apellidos, telefono, direccion, correo, contrasenia);
        this.Carrera = Carrera;
    }
    public String getCarrera() {
        return Carrera;
    }

    public void setCarrera(String Carrera) {
        this.Carrera = Carrera;
    }

    public String getArea_de_interes() {
        return Area_de_interes;
    }

    public void setArea_de_interes(String Area_de_interes) {
        this.Area_de_interes = Area_de_interes;
    }

    public String getDisponibilidad() {
        return Disponibilidad;
    }

    public void setDisponibilidad(String Disponibilidad) {
        this.Disponibilidad = Disponibilidad;
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }

    public List<Actividad> getPeticionesActividades() {
        return peticionesActividades;
    }

    // Métodos Getters y Setters
    public void setPeticionesActividades(List<Actividad> peticionesActividades) {
        this.peticionesActividades = peticionesActividades;
    }

    // Método toString
    @Override
    public String toString() {
        return "Alumno: "+super.getNombre()+" "+super.getApellidos()+" ID: "+super.getId();
    }
    
}
