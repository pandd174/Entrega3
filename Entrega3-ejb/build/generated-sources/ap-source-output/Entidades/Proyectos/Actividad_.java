package Entidades.Proyectos;

import Entidades.Proyectos.Proyecto;
import Entidades.Usuarios.Alumno;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-04T18:32:07")
@StaticMetamodel(Actividad.class)
public class Actividad_ { 

    public static volatile SingularAttribute<Actividad, String> descripcion;
    public static volatile SingularAttribute<Actividad, Date> fecha_inicio;
    public static volatile SingularAttribute<Actividad, String> conocimientos_necesarios;
    public static volatile SingularAttribute<Actividad, Proyecto> proyecto;
    public static volatile SingularAttribute<Actividad, Long> id;
    public static volatile SingularAttribute<Actividad, Date> fecha_finalizacion;
    public static volatile SingularAttribute<Actividad, String> nombre;
    public static volatile ListAttribute<Actividad, Alumno> peticionarios;
    public static volatile ListAttribute<Actividad, Alumno> participantes;

}