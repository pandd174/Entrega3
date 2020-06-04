/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidades.Usuarios;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import Entidades.Proyectos.Actividad;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NamedQueries({
    @NamedQuery(query = "Select u from Usuario u where u.correo = :email and u.hashPassword = :cont", name = "encontrar usuario"), 
    @NamedQuery(query = "Select COUNT(u) from Usuario u where u.correo = :email and u.hashPassword = :cont", name = "numero ususarios")
})

public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;
    @Column(name = "apellidos", nullable = false, length = 50)
    private String apellidos;
    @Column(name = "telefono", nullable = false, length = 50)
    private String telefono;
    @Column(name = "direccion", nullable = true, length = 100)
    private String Direccion;
    @Column(name = "correo", nullable = false, length = 100)
    private String correo;
    @Column(name = "hashPassword", nullable = false, length = 50)
    private String hashPassword;    
    
    public Usuario() {}
    
    public Usuario (String nombre, String apellidos, String telefono, String direccion, String correo,String contrasenia)
    {
        setNombre(nombre);
        setHashPassword(contrasenia);
        setApellidos(apellidos);
        setTelefono(telefono);
        setDireccion(direccion);
        setCorreo(correo);
    }
    
    // Métodos Getters y Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getHashPassword() {
        return hashPassword;
    }

    public void setHashPassword(String hashPassword) {
        this.hashPassword = hashPassword;
    }

    
    
        
    // Métodos relacionados con equals
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    // Método toString
    @Override
    public String toString() {
        return "usuarios.Usuario[ id=" + id + " ]";
    }
    
}