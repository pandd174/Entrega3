/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Usuarios;

import Entidades.Proyectos.Proyecto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class ONG extends Usuario implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "nombreOng",nullable = false,length = 100)
    private String nombreOng;
    @Column(name = "descripcion",nullable = false,length = 500)
    private String descripcion; //Objetivo de la ONG
    
    @OneToMany (mappedBy="ong")
    private List<Proyecto> proyectos; //1 ONG organiza varios proyectos
    
    
    public ONG(){}

    public ONG (String nombre, String apellidos, String telefono, String direccion, String correo,String contrasenia, String nombreONG, String descripcion)
    {
        super(nombre, apellidos, telefono, direccion, correo, contrasenia);
        this.setNombreOng(nombreONG);
        this.setDescripcion(descripcion);
        this.setProyectos(new ArrayList<>());
    }
    
    // Métodos Getters y Setters

    public String getNombreOng() {
        return nombreOng;
    }

    public void setNombreOng(String nombreOng) {
        this.nombreOng = nombreOng;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(List<Proyecto> proyectos) {
        this.proyectos = proyectos;
    }
   
    
    // Métodos relacionados con equals
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (super.getId() != null ? super.getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ONG)) {
            return false;
        }
        ONG other = (ONG) object;
        if ((super.getId() == null && other.getId() != null) || (super.getId() != null && !super.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }
    
}
