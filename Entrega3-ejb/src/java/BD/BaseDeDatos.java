/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import Entidades.Proyectos.Actividad;
import Entidades.Proyectos.Proyecto;
import Entidades.Usuarios.Alumno;
import Entidades.Usuarios.Docencia;
import Entidades.Usuarios.ONG;
import Entidades.Usuarios.Usuario;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.ejb.EJBException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Alejandro
 */
@Stateless
public class BaseDeDatos implements BaseDeDatosLocal {

    @PersistenceContext(unitName = "Trabajo-GrupalPU")
    private EntityManager em;

    @Override
    public void aniadirUsuario(Usuario usr) {
        em.persist(usr);
    }

    @Override
    public void aniadirActividad(Actividad a) {
        em.persist(a);
    }
    
    @Override
    public void aniadirProyecto(Proyecto p) {
        em.persist(p);
    }
    
    @Override
    public Usuario encontrarUsuario(String correo, String contrasenia) {

        try {
            Query query = em.createNamedQuery("encontrar usuario");
            query.setParameter("email", correo);
            query.setParameter("cont", contrasenia);
            List<Usuario> list = query.getResultList();
            return list.get(0);
        } catch (Exception e) {
            return null;
        }
      
        
    }

    @Override
    public List<Proyecto> misProyectosDocencia(Long id) {
       Query query = em.createNamedQuery("mis proyectos docencia");
       query.setParameter("ident", id);
       return query.getResultList();
    }
    
    @Override
    public List<Proyecto> misProyectosONG(Long id) {
       Query query = em.createNamedQuery("mis proyectos ong");
       query.setParameter("ident", id);
       return query.getResultList();
    }

    @Override
    public Proyecto encontrarProyecto(Long id) {
       Query query = em.createNamedQuery("encontrar proyecto");
        query.setParameter("ident", id);
        List<Proyecto> list = query.getResultList();
        return list.get(0);
    }

    @Override
    public void eliminarProyecto(Proyecto p) {
        p.getOng().getProyectos().remove(p);
        
        p.getPeticionesDocente().clear();

        for (Docencia d : p.getCoordinatedByDocencia()){
            d.getCoordinaProyecto().remove(p);
            actualizar(d);
        }
        
        
        p = em.merge(p);
        /*
        Query query = em.createNativeQuery("delete from jnd_proyecto_docencia_peticiones where proyecto_fk = " + p.getId());
        query.executeUpdate();
        query = em.createNativeQuery("delete from jnd_proyecto_docencia where proyecto_fk = " + p.getId());
        query.executeUpdate();
        */
        em.remove(p);
    }

    @Override
    public void editarProyecto(Long id, String loc, String desc) {
        Query query = em.createNamedQuery("editar proyecto");
        query.setParameter("loc", loc);
        query.setParameter("desc", desc);
        query.setParameter("ident", id);
        query.executeUpdate();
    }

    @Override
    public void editarActividad(Long id, String descripcion, String conocimientos, Date fecha_inicio, Date fecha_final) {
        Query query = em.createNamedQuery("editar actividad");
        query.setParameter("desc", descripcion);
        query.setParameter("conoc", conocimientos);
        query.setParameter("fecinit", fecha_inicio);
        query.setParameter("fecfin", fecha_final);
        query.setParameter("ident", id);
        query.executeUpdate();
    }
    
    @Override
    public void eliminarActividad(Long id) {
        Query query = em.createQuery("Select a from Actividad a where a.id = :ident");
        query.setParameter("ident", id);
        Actividad a = (Actividad) query.getSingleResult();
        Proyecto p = a.getProyecto();
        p.getActividades().remove(a);
        em.remove(query.getSingleResult());
    }

    @Override
    public void aceptarPeticion(Docencia doc, Proyecto proy) {
        proy.getPeticionesDocente().remove(doc);
        doc.getCoordinaProyecto().add(proy);
        proy.getCoordinatedByDocencia().add(doc);
        Proyecto p = em.merge(proy);
        Docencia d = em.merge(doc);
        p.getPeticionesDocente().remove(d);
        d.getCoordinaProyecto().add(p);
    }

    @Override
    public void rechazarPeticion(Docencia doc, Proyecto proy) {
        Proyecto p = em.merge(proy);
        Docencia d = em.merge(doc);
        p.getPeticionesDocente().remove(d);
        proy.getPeticionesDocente().remove(doc);
    }

    @Override
    public List<Proyecto> todosProyectosNo(Docencia doc) {
        Query query = em.createQuery("Select p from Proyecto p");
        List<Proyecto> listQuery = query.getResultList();
        List<Proyecto> listAux = new ArrayList<>();
        for(Proyecto p : listQuery)
            if(!doc.getCoordinaProyecto().contains(p) && !p.getPeticionesDocente().contains(doc)){
                /*Proyecto pnew = new Proyecto(p.getNombre(), p.getDescripcion(), p.getLocalidad());
                pnew.setId(p.getId());
                pnew.setOng(p.getOng());*/
                listAux.add(p);
            }
        return listAux;
    }

    @Override
    public List<Actividad> misActividadesDocencia(Docencia doc) {
        Query query = em.createQuery("Select a from Actividad a");
        List<Actividad> listQuery = query.getResultList();
        List<Actividad> listAux = new ArrayList<>();
        for(Actividad a : listQuery)
            if(!doc.getCoordinaProyecto().contains(a.getProyecto()))
                listAux.add(a);
        return listAux;
    }

    @Override
    public void aniadirProyectoPeticion(Proyecto proy, Docencia doc) {
        
        //proy.getPeticionesDocente().add(doc);
        //doc.getPeticionesProyectos().add(proy);
        
        Proyecto p = em.merge(proy);
        Docencia d = em.merge(doc);
        p.getPeticionesDocente().add(d);
        d.getPeticionesProyectos().add(p);
        /*
        Query query = em.createQuery("Select p from Proyecto p where p.nombre = :ident");
        query.setParameter("ident", proy.getNombre());
        Proyecto p = (Proyecto) query.getSingleResult();
        Docencia d = em.merge(doc);
        p.getPeticionesDocente().add(d);*/
    }

    @Override
    public List<Actividad> actividadesAlumno(Alumno a) {
        Query query = em.createQuery("Select a from Actividad a");
        List<Actividad> aux = new ArrayList<>();
        List<Actividad> listaQuery = query.getResultList();
        for(Actividad act : listaQuery){
            if(act.getParticipantes().contains(a)) aux.add(act);
        }
        return aux;
    }

    @Override
    public List<Actividad> todasActividadesNo(Alumno a) {
       Query query = em.createQuery("Select a from Actividad a");
       List<Actividad> aux = new ArrayList<>();
       List<Actividad> listaQuery = query.getResultList();
       for(Actividad act : listaQuery){
            if(!act.getParticipantes().contains(a) && !act.getPeticionarios().contains(a)) aux.add(act);
        }
       return aux;
    }

    @Override
    public void salirActividad(Alumno a, Actividad act) {
        act.getParticipantes().remove(a);
        Alumno al = em.merge(a);
        Actividad acti = em.merge(act);
        al.getActividades().remove(acti);
        a.getActividades().remove(act);
    }

    @Override
    public void mandarPeticionAlumno(Actividad a, Alumno al) {
        //a.getPeticionarios().add(al);
        Alumno alu = em.merge(al);
        Actividad acti = em.merge(a);
        actualizar(acti);
        acti.getPeticionarios().add(alu);
        //a.getPeticionarios().add(al);
    }

    @Override
    public void aceptarPeticionAlumno(Alumno alumno, Actividad actividad) {
        
        actividad.getPeticionarios().remove(alumno);
        alumno.getActividades().add(actividad);
        actividad.getParticipantes().add(alumno);
        Actividad a = em.merge(actividad);
        Alumno al = em.merge(alumno);
        a.getPeticionarios().remove(al);
        al.getActividades().add(a);
    }

    @Override
    public void rechazarPeticionAlumno(Alumno alumno, Actividad actividad) {
        
        actividad.getPeticionarios().remove(alumno);
        Actividad a = em.merge(actividad);
        Alumno al = em.merge(alumno);       
        a.getPeticionarios().remove(al);
        
    }

    @Override
    public Object  actualizar(Object o) {
        Object res = em.merge(o);
        em.persist(res);
        return res;
    }

    @Override
    public List<Actividad> misActividadesONG(ONG ong) {
       Query query = em.createQuery("Select a from Actividad a");
       List<Actividad> listaActividades = query.getResultList();
       List<Actividad> result = new ArrayList<>();
       
       for(Proyecto proyecto : ong.getProyectos()){
           for(Actividad actividad : listaActividades){
               if(actividad.getProyecto().equals(proyecto)){
                   result.add(actividad);
               }
           }
       }
       for(Actividad a: result)System.out.println("Actividad: "+a.toString());
       return result;
    }
  
}
