/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import Entidades.Proyectos.Actividad;
import Entidades.Proyectos.Proyecto;
import javax.persistence.NamedQuery;

@Entity
@DiscriminatorValue("Docencia")
public class Docencia extends Usuario implements Serializable {

    @Column(name = "Area_de_Estudio", nullable = false, length = 200)
    private String Area_de_Estudio;
    @Column(name = "Departamento", nullable = false, length = 200)
    private String Departamento;
    @ManyToMany(mappedBy = "peticionesDocente")
    private List<Proyecto> peticionesProyectos;
    @ManyToMany
    @JoinTable(name = "jnd_Proyecto_Docencia", joinColumns = @JoinColumn(name = "Proyecto_fk"), inverseJoinColumns = @JoinColumn(name = "Docencia_fk"))
    private List<Proyecto> CoordinaProyecto;
    
    public Docencia(){}
    public Docencia(String nombre, String apellidos, String telefono, String direccion, String correo,String contrasenia,String area_estudio, String departamento){
        super(nombre, apellidos, telefono, direccion, correo, contrasenia);
        this.Area_de_Estudio = area_estudio;
        this.Departamento = departamento;
        this.CoordinaProyecto = new ArrayList();
    }

    // Métodos Getters y Setters

    public String getArea_de_Estudio() {
        return Area_de_Estudio;
    }

    public void setArea_de_Estudio(String Area_de_Estudio) {
        this.Area_de_Estudio = Area_de_Estudio;
    }

    public String getDepartamento() {
        return Departamento;
    }

    public void setDepartamento(String Departamento) {
        this.Departamento = Departamento;
    }

    public List<Proyecto> getPeticionesProyectos() {
        return peticionesProyectos;
    }

    public void setPeticionesProyectos(List<Proyecto> peticionesProyectos) {
        this.peticionesProyectos = peticionesProyectos;
    }

    public List<Proyecto> getCoordinaProyecto() {
        return CoordinaProyecto;
    }

    public void setCoordinaProyecto(List<Proyecto> CoordinaProyecto) {
        this.CoordinaProyecto = CoordinaProyecto;
    }

    
    // Este método es usado en ControlProyectos.java para obtener toda la lista de Actividades perteneciente a cada uno de los proyectos asociados
    public boolean busquedaActividad(Actividad act) {
        for (Proyecto next: CoordinaProyecto){
            for (Actividad next1: next.getActividades()){
                if(next1.equals(act)){
                    return true;
                }
            }
        }
        return false;
    }
    // Método usado en ControlProyectos.java que nos devuelve una lista de todas las actividades de los proyectos coordinados
    public List<Actividad> getActividadesDocencia(){
        List<Actividad> aux = new ArrayList<Actividad>();
        for(Proyecto next: CoordinaProyecto){
            for (Actividad next1:next.getActividades()){
                 aux.add(next1);
            }
        }
        return aux;
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
        if (!(object instanceof Docencia)) {
            return false;
        }
        Docencia other = (Docencia) object;
        if ((super.getId() == null && other.getId() != null) || (super.getId() != null && !super.getId().equals(other.getId()))) {
            return false;
        }
        return true;
    }
    
    // Método toString
    @Override
    public String toString() {
        return "Personal Docente e Investigador: "+super.getNombre()+" "+super.getApellidos()+" Departamento: " + this.Departamento + " ID: "+super.getId();
    }
    
}
