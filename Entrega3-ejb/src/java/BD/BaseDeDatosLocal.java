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
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Alejandro
 */
@Local
public interface BaseDeDatosLocal {
    void aniadirUsuario(Usuario usr);
    Usuario encontrarUsuario(String correo, String contrasenia);
    
    void aniadirActividad(Actividad a);
    void eliminarActividad(Long id);
    List<Actividad> misActividadesDocencia(Docencia doc);
    void editarActividad(Long id, String descripcion, String conocimientos, Date fecha_inicio, Date fecha_final);
    
    void aniadirProyecto(Proyecto p);
    void eliminarProyecto(Proyecto p);
    List<Proyecto> todosProyectosNo(Docencia doc);
    Proyecto encontrarProyecto(Long id);
    List<Proyecto> misProyectosONG(Long id);
    void editarProyecto(Long id, String loc, String desc);
    
    List<Actividad> actividadesAlumno(Alumno a);
    List<Actividad> todasActividadesNo(Alumno a);
    void salirActividad(Alumno a, Actividad act);
    
    void aceptarPeticion(Docencia doc, Proyecto proy);
    void rechazarPeticion(Docencia doc, Proyecto proy);
    void aniadirProyectoPeticion(Proyecto proy, Docencia doc);

    public void mandarPeticionAlumno(Actividad a, Alumno al);

    public void aceptarPeticionAlumno(Alumno alumno, Actividad actividad);

    public void rechazarPeticionAlumno(Alumno alumno, Actividad actividad);
    public List<Proyecto> misProyectosDocencia(Long id);
    Object actualizar(Object o);

    public List<Actividad> misActividadesONG(ONG ong);
}
