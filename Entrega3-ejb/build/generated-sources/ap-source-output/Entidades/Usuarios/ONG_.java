package Entidades.Usuarios;

import Entidades.Proyectos.Proyecto;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2020-06-04T18:32:07")
@StaticMetamodel(ONG.class)
public class ONG_ extends Usuario_ {

    public static volatile SingularAttribute<ONG, String> descripcion;
    public static volatile ListAttribute<ONG, Proyecto> proyectos;
    public static volatile SingularAttribute<ONG, String> nombreOng;

}