package Parcial2_Web.Classes;

public class Usuario {
    

    public int id_usuario;
    public String nombre_usuario;
    public String rol_usuario; 


    public Usuario() {
    }


    public Usuario(int id_usuario, String nombre_usuario, String rol_usuario) {
        this.id_usuario = id_usuario;
        this.nombre_usuario = nombre_usuario;
        this.rol_usuario = rol_usuario;
    }


    public int getId_usuario() {
        return this.id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre_usuario() {
        return this.nombre_usuario;
    }

    public void setNombre_usuario(String nombre_usuario) {
        this.nombre_usuario = nombre_usuario;
    }

    public String getRol_usuario() {
        return this.rol_usuario;
    }

    public void setRol_usuario(String rol_usuario) {
        this.rol_usuario = rol_usuario;
    }

}
