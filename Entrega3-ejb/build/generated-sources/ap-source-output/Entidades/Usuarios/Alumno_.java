package Entidades.Usuarios;

import Entidades.Proyectos.Actividad;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-04T18:32:07")
@StaticMetamodel(Alumno.class)
public class Alumno_ extends Usuario_ {

    public static volatile SingularAttribute<Alumno, String> Area_de_interes;
    public static volatile SingularAttribute<Alumno, String> Carrera;
    public static volatile SingularAttribute<Alumno, String> Disponibilidad;
    public static volatile ListAttribute<Alumno, Actividad> actividades;
    public static volatile ListAttribute<Alumno, Actividad> peticionesActividades;

}