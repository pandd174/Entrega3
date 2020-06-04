package Entidades.Usuarios;

import Entidades.Proyectos.Proyecto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-04T18:32:07")
@StaticMetamodel(Docencia.class)
public class Docencia_ extends Usuario_ {

    public static volatile SingularAttribute<Docencia, String> Area_de_Estudio;
    public static volatile ListAttribute<Docencia, Proyecto> CoordinaProyecto;
    public static volatile ListAttribute<Docencia, Proyecto> peticionesProyectos;
    public static volatile SingularAttribute<Docencia, String> Departamento;

}