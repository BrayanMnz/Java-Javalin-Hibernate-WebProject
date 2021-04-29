package Parcial2_Web.Classes;

import java.io.Serializable;

public class Form_JSON  implements Serializable  { 


    public int id_formulario;
    public String nombre;

    public String sector;

    public String nivel_escolar;

    public String usuario_formulario;

    public String latitud;
    public String longitud;
    public String mimeType;
    public String fotoBase64;

    public Form_JSON() {
    }

    public Form_JSON(int id_formulario, String nombre, String sector, String nivel_escolar, String usuario_formulario, String latitud, String longitud,  String mimeType, String foto) {
        this.id_formulario = id_formulario;
        this.nombre = nombre;
        this.sector = sector;
        this.nivel_escolar = nivel_escolar;
        this.usuario_formulario = usuario_formulario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.mimeType = mimeType;
        this.fotoBase64 = foto;
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

    public String getUsuario_formulario() {
        return this.usuario_formulario;
    }

    public void setUsuario_formulario(String usuario_formulario) {
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

    public String getMimeType() { return mimeType; }

    public void setMimeType(String mimeType) { this.mimeType = mimeType; }

    public String getFotoBase64() { return fotoBase64; }

    public void setFotoBase64(String fotoBase64) { this.fotoBase64 = fotoBase64; }

}