/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;
import BD.BaseDeDatosLocal;
import Entidades.Proyectos.Actividad;
import Entidades.Usuarios.Alumno;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

@Named(value = "controlAlumno")
@RequestScoped
public class ControlAlumno implements Serializable {
    @Inject
    BaseDeDatosLocal bbdd;
    @Inject
    private ControlAutorizacion ctrl;
     
    public ControlAlumno() {}
    
    //Devulve las actividades en las que esta apuntado un alumno
    public List<Actividad> getMisActividades() {
        Alumno a = (Alumno) ctrl.getUsuario();
        return bbdd.actividadesAlumno(a);
    }
    // Devuelve todas las actividades de la base de datos en las que el alumno no está participando
    public List<Actividad> getTodasActividadesAlumno() {
        Alumno a = (Alumno) ctrl.getUsuario();
        return bbdd.todasActividadesNo(a);
    }
    // Elimina una actividad en concreto pasándole como parámetro el objeto que debe ser borrado
    public void salir(Actividad a){
        Alumno al = (Alumno) ctrl.getUsuario();
        bbdd.salirActividad(al, a);
    }
    //Manda una peticion para participar en una actividad a un Docente
    public void mandarPeticion(Actividad a){
        Alumno al = (Alumno) ctrl.getUsuario();
        bbdd.mandarPeticionAlumno(a,al);
    }
}