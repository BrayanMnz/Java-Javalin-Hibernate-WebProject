package Parcial2_Web.Classes;

import javax.persistence.*;
import java.util.*;
import Parcial2_Web.Classes.Formulario;

@Entity
public class Usuario {
    
    // @Id    
    // @GeneratedValue(strategy = GenerationType.IDENTITY) // Se genera el ID automatico
    // public int id_usuario;

    @Id
    @Column
    public String username;
    @Column
    public String passwd_usuario;
    @Column
    public String nombre_usuario;
    @Column
    public String rol_usuario; 
    
    @OneToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL , orphanRemoval = true)
    private List<Formulario> formularios = new ArrayList<Formulario>();

    
    
    public Usuario() {
    }


    public Usuario(String username, String passwd_usuario, String nombre_usuario, String rol_usuario) {
        // this.id_usuario = id_usuario;
        this.username = username;
        this.passwd_usuario = passwd_usuario;
        this.nombre_usuario = nombre_usuario;
        this.rol_usuario = rol_usuario;
    }


    // public int getId_usuario() {
    //     return this.id_usuario;
    // }

    // public void setId_usuario(int id_usuario) {
    //     this.id_usuario = id_usuario;
    // }

    public String getusername() {
        return this.username;
    }

    public void setusername(String username) {
        this.username = username;
    }

    public String getRol_usuario() {
        return this.rol_usuario;
    }

    public void setRol_usuario(String rol_usuario) {
        this.rol_usuario = rol_usuario;
    }

    public String getPasswd_usuario() {
        return this.passwd_usuario;
    }

    public void setPasswd_usuario(String passwd_usuario) {
        this.passwd_usuario = passwd_usuario;
    }
    

    public String getNombre_usuario() {
        return this.nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

}
