/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Proyectos;

import Entidades.Usuarios.Alumno;
import java.io.Serializable;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity

@NamedQueries(  {
    @NamedQuery(query = "Select a from Actividad a JOIN a.proyecto p where p.ong.id = :ident",name = "actividades ong"),
    @NamedQuery(query = "Update Actividad a SET a.descripcion = :desc , a.conocimientos_necesarios = :conoc , a.fecha_inicio = :fecinit "
                        + ", a.fecha_finalizacion = :fecfin where a.id = :ident", name = "editar actividad")
})

public class Actividad implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Column(name = "descripcion", nullable = false, length = 500)
    private String descripcion;
    @Column(name = "conocimientos_necesarios", nullable = false, length = 500)
    private String conocimientos_necesarios;
    @Column(name = "fecha_inicio", nullable = false, length = 50)
    @Temporal(TemporalType.DATE)
    private Date fecha_inicio;
    @Column(name = "fecha_finalizacion", nullable = true, length = 50)
    @Temporal(TemporalType.DATE)
    private Date fecha_finalizacion;
    @ManyToMany(mappedBy = "actividades")
    private List<Alumno> participantes;
    @ManyToOne
    private Proyecto proyecto;
    @ManyToMany
    @JoinTable(name = "jnd_Alumno_Actividad_Peticiones", joinColumns = @JoinColumn(name = "Actividad_fk"), inverseJoinColumns = @JoinColumn(name = "Alumno_fk"))
    private List<Alumno> peticionarios;

    public Actividad(){}
    
    public Actividad(String nombre, String descripcion, String conocimientos, Date fecha_inicio, Date fecha_final, Proyecto p){
        setNombre(nombre);
        setDescripcion(descripcion);
        setConocimientos_necesarios(conocimientos);
        setFecha_inicio(fecha_inicio);
        setFecha_finalizacion(fecha_final);
        setProyecto(p);
    }
    
    // Métodos Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getConocimientos_necesarios() {
        return conocimientos_necesarios;
    }

    public void setConocimientos_necesarios(String conocimientos_necesarios) {
        this.conocimientos_necesarios = conocimientos_necesarios;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_finalizacion() {
        return fecha_finalizacion;
    }

    public void setFecha_finalizacion(Date fecha_finalizacion) {
        this.fecha_finalizacion = fecha_finalizacion;
    }

    public List<Alumno> getParticipantes() {
        return participantes;
    }

    public void setParticipantes(List<Alumno> participantes) {
        this.participantes = participantes;
    }

    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public List<Alumno> getPeticionarios() {
        return peticionarios;
    }

    public void setPeticionarios(List<Alumno> peticionarios) {
        this.peticionarios = peticionarios;
    }
   
    
    // Métodos relacionados con equals
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actividad)) {
            return false;
        }
        Actividad other = (Actividad) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    // Método toString
    @Override
    public String toString() {
        return id.toString();
    }
    
}
