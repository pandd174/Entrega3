/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;


import BD.BaseDeDatosLocal;
import Entidades.Usuarios.Alumno;
import Entidades.Usuarios.Docencia;
import Entidades.Usuarios.ONG;
import Entidades.Usuarios.Usuario;
import java.text.ParseException;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;


@Named(value = "login")
@RequestScoped
public class Login {

    @Inject
    BaseDeDatosLocal bbdd;
    
    private String correo;
    private String contrasenia;
    
    @Inject
    private ControlAutorizacion ctrl;
    
    /**
     * Creates a new instance of Login
     */
    public Login() {}
    
    // Método de autentificación del inicio de sesión
    public String autenticar() throws ParseException {
        
        FacesContext ctx = FacesContext.getCurrentInstance();
        
        Usuario user = bbdd.encontrarUsuario(correo, contrasenia);
        
        if (user == null){
            return null; 
        }
        
        String rol = user.getClass().toString().substring(25);
        
        switch(rol){
            case "Alumno":
                Alumno alumno = (Alumno) user;
                ctrl.setUsuario(alumno);
                ctrl.setRol(0);
                return ctrl.homeAlumno();
                
            case "Docencia":
                Docencia docencia = (Docencia) user;
                ctrl.setUsuario(docencia);
                ctrl.setRol(2);
                return ctrl.homeDocencia();
                
            case "ONG":
                ONG ong = (ONG) user;
                ctrl.setUsuario(ong);
                ctrl.setRol(1);
                return ctrl.homeONG();
        }
        
        ctx.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Correo o contraseña no valido!", "Correo o contraseña no valido!"));
        return null;
    }
    
    // Getters y setters útiles
    public String getCorreo() {
        return correo;
    }
    public String getContrasenia() {
        return contrasenia;
    }
    
    public void setCorreo(String correo) {
        this.correo = correo;
    }
    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }
    
}
