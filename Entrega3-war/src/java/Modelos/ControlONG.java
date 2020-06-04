/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;
import BD.BaseDeDatosLocal;
import Entidades.Proyectos.Actividad;
import Entidades.Proyectos.Proyecto;
import Entidades.Usuarios.Docencia;
import Entidades.Usuarios.ONG;
import java.io.Serializable;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import java.text.SimpleDateFormat;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import utils.PeticionAdmin;

@Named(value = "ControlONG")
@RequestScoped
public class ControlONG implements Serializable {
     @Inject
    BaseDeDatosLocal bbdd;
     @Inject
    private ControlAutorizacion ctrl;
    
    //Atributos
    private Proyecto proyecto;
    private Actividad actividad;
    String fecInit;
    String fecFin;

    public ControlONG(){
        proyecto = new Proyecto();
        actividad = new Actividad();
    
    }
    // METODOS DEL USUARIO ONG
    
    //Devuelve los proyectos de una ONG
     public List<Proyecto> getMisProyectos(){
        return bbdd.misProyectosONG(ctrl.getUsuario().getId());
        //ONG ong = (ONG) ctrl.getUsuario();
        //return ong.getProyectos();
    }
    //Devuelve TODAS las actividades de una ONG
    public List<Actividad> getListaActividades(){
       ONG ong =(ONG)ctrl.getUsuario();
       /*List<Actividad> listRes = new ArrayList<>();
       for(Proyecto p : ong.getProyectos())
           listRes.addAll(p.getActividades());
       return listRes;*/
       return bbdd.misActividadesONG(ong);
    }
    // Método que usamos para crear un proyecto y añadirlo a la base de datos
    public void crearProyecto(){
        ONG ong =(ONG)ctrl.getUsuario();
        proyecto.setOng(ong);
        ong.getProyectos().add(proyecto);
        bbdd.aniadirProyecto(proyecto);
    }
    
    //Crea una actividad en la base de datos
    public void crearActividad(String id) throws ParseException{
        Long ident = Long.parseLong(id);
        actividad.setFecha_inicio(new SimpleDateFormat("dd/MM/yyyy").parse(fecInit));
        actividad.setFecha_finalizacion(new SimpleDateFormat("dd/MM/yyyy").parse(fecFin));
        ONG ong = (ONG) ctrl.getUsuario();
        Proyecto p = null;
        for(Proyecto q: ong.getProyectos()){
            if (q.getId().equals(ident)){
                p = q;
            }
        }
        actividad.setProyecto(p);
        actividad = (Actividad) bbdd.actualizar(actividad);
        p.addActividad(actividad);
        bbdd.actualizar(p);   
    }
    //Edita un proyecto en la base de datos
    public void editarProyecto(String id){
        Long ident = Long.parseLong(id);
        bbdd.editarProyecto(ident,proyecto.getLocalidad(),proyecto.getDescripcion());
    }
    //Edita una actividad en la base de datos
    public void editarActividad(String id) throws ParseException{
        Long ident = Long.parseLong(id);
        actividad.setFecha_inicio(new SimpleDateFormat("dd/MM/yyyy").parse(fecInit));
        actividad.setFecha_finalizacion(new SimpleDateFormat("dd/MM/yyyy").parse(fecFin));
        bbdd.editarActividad(ident, actividad.getDescripcion(), actividad.getConocimientos_necesarios(), actividad.getFecha_inicio(), actividad.getFecha_inicio());
    }
    // Elimina un proyecto en concreto 
    public void borrarProyecto(Proyecto p){
       bbdd.eliminarProyecto(p);
    }
    // Elimina una actividad en concreto mediante su id 
    public void borrarActividad(String id){
       Long ident = Long.parseLong(id);
       bbdd.eliminarActividad(ident);
    }
    // Devuelve toda la lista de peticiones de los proyectos actuales
    public List<PeticionAdmin> getMisPeticiones(){
        ONG ong = (ONG) ctrl.getUsuario();
        List<PeticionAdmin> listaRes = new ArrayList<>();
        List<Proyecto> misProyectos = bbdd.misProyectosONG(ong.getId());
        for(Proyecto p : misProyectos)
            for(Docencia d : p.getPeticionesDocente())
                listaRes.add(new PeticionAdmin(d,p));
            
        return listaRes;
    }
    //Métodos para aceptar, rechazar y enviar peticiones 
    public void aceptarPeticion(Docencia doc, Proyecto proy){
        bbdd.aceptarPeticion(doc, proy);
    }
    public void rechazarPeticion(Docencia doc, Proyecto proy){
         bbdd.rechazarPeticion(doc, proy);
    }
     public String getFecInit() {
        return fecInit;
    }

    public void setFecInit(String fecInit) {
        this.fecInit = fecInit;
    }

    public String getFecFin() {
        return fecFin;
    }

    public void setFecFin(String fecFin) {
        this.fecFin = fecFin;
    }
    
    public Actividad getActividad() {
        return actividad;
    }

    public void setActividad(Actividad actividad) {
        this.actividad = actividad;
    }
    
    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }
}
