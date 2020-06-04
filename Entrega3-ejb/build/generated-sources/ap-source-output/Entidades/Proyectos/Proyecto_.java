package Entidades.Proyectos;

import Entidades.Proyectos.Actividad;
import Entidades.Usuarios.Docencia;
import Entidades.Usuarios.ONG;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-04T18:32:07")
@StaticMetamodel(Proyecto.class)
public class Proyecto_ { 

    public static volatile SingularAttribute<Proyecto, String> descripcion;
    public static volatile ListAttribute<Proyecto, Docencia> peticionesDocente;
    public static volatile SingularAttribute<Proyecto, ONG> ong;
    public static volatile SingularAttribute<Proyecto, String> localidad;
    public static volatile SingularAttribute<Proyecto, Long> id;
    public static volatile ListAttribute<Proyecto, Actividad> actividades;
    public static volatile SingularAttribute<Proyecto, String> nombre;
    public static volatile ListAttribute<Proyecto, Docencia> coordinatedByDocencia;

}