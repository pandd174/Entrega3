/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;
import BD.BaseDeDatosLocal;
import Entidades.Proyectos.Actividad;
import Entidades.Proyectos.Proyecto;
import Entidades.Usuarios.Alumno;
import Entidades.Usuarios.Docencia;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import utils.PeticionAlumno;

@Named(value = "ControlDocencia")
@RequestScoped
public class ControlDocencia implements Serializable {
    @Inject
    BaseDeDatosLocal bbdd;
     @Inject
    private ControlAutorizacion ctrl;
    
    public ControlDocencia() throws ParseException {}

    // Devuelve la lista de peticiones hechas por los alumnos a las distintas actividades
    public List<PeticionAlumno> getMisPeticionesAlumno(){
        Docencia doc = (Docencia) ctrl.getUsuario();
        List<PeticionAlumno> listaRes = new ArrayList<>();
        for(Proyecto p : doc.getCoordinaProyecto())
            for(Actividad a : p.getActividades())
                for (Alumno al : a.getPeticionarios()) {
                    listaRes.add(new PeticionAlumno(al, a));
                }
            
        return listaRes;
    }
    //Acepta una peticion de un alumno
     public void aceptarPeticionAlumno(PeticionAlumno p){
        bbdd.aceptarPeticionAlumno(p.getAlumno(),p.getActividad());
    }
    //Rechaza una peticion de un alumno
    public void rechazarPeticionAlumno(PeticionAlumno p){
        bbdd.rechazarPeticionAlumno(p.getAlumno(),p.getActividad());
    }
    // Devuelve la lista de los proyectos almacenados en la clase docencia
    public List<Proyecto> getMisProyectosDocencia(){
        Docencia docencia = (Docencia) ctrl.getUsuario();
        return docencia.getCoordinaProyecto();
    }
    // Devuelve todos los proyectos de la base de datos en las que el personal docente no está participando
    public List<Proyecto> getTodosProyectosNoDocente() {
        Docencia docencia = (Docencia) ctrl.getUsuario();
        return bbdd.todosProyectosNo(docencia);
    }
    // Añadimos una nueva petición a la lista de peticiones
    public void aniadirProyectoPeticion(Proyecto proy){
        Docencia doc = (Docencia) ctrl.getUsuario();
        bbdd.aniadirProyectoPeticion(proy, doc);
    }
    // Devuelve todas las actividades que coordina el personal docente
    public List<Actividad> getMisActividadesDocencia(){
        Docencia doc = (Docencia) ctrl.getUsuario();
        List<Actividad> listaRes = new ArrayList<>();
        for(Proyecto p : doc.getCoordinaProyecto())
            for(Actividad a : p.getActividades())
                listaRes.add(a);
        return listaRes;
    }
    public List<Actividad> getListaActividades(){
       Docencia doc =(Docencia)ctrl.getUsuario();
       return doc.getActividadesDocencia();
    }
}
