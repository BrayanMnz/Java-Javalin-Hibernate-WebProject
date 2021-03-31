package Parcial2_Web.Classes;


public class Formulario {
    
    public int id_formulario;
    public String nombre;
    public String sector;
    public String nivel_escolar;

    public Usuario usuario_formulario;
    public String latitud;
    public String longitud;


    public Formulario() {
    }


    public Formulario(int id_formulario, String nombre, String sector, String nivel_escolar, Usuario usuario_formulario, String latitud, String longitud) {
        this.id_formulario = id_formulario;
        this.nombre = nombre;
        this.sector = sector;
        this.nivel_escolar = nivel_escolar;
        this.usuario_formulario = usuario_formulario;
        this.latitud = latitud;
        this.longitud = longitud;
    }



    public int getId_formulario() {
        return this.id_formulario;
    }

    public void setId_formulario(int id_formulario) {
        this.id_formulario = id_formulario;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSector() {
        return this.sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public String getNivel_escolar() {
        return this.nivel_escolar;
    }

    public void setNivel_escolar(String nivel_escolar) {
        this.nivel_escolar = nivel_escolar;
    }

    public Usuario getUsuario_formulario() {
        return this.usuario_formulario;
    }

    public void setUsuario_formulario(Usuario usuario_formulario) {
        this.usuario_formulario = usuario_formulario;
    }

    public String getLatitud() {
        return this.latitud;
    }

    public void setLatitud(String latitud) {
        this.latitud = latitud;
    }

    public String getLongitud() {
        return this.longitud;
    }

    public void setLongitud(String longitud) {
        this.longitud = longitud;
    }


}

