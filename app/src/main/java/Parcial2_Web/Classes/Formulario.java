package Parcial2_Web.Classes;

import javax.persistence.*;

import java.io.Serializable;
import java.util.*;
import Parcial2_Web.Classes.Usuario;

@Entity
public class Formulario  implements Serializable  {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Se genera el ID automatico
    public int id_formulario;
    @Column
    public String nombre;
    @Column
    public String sector;
    @Column
    public String nivel_escolar;
    @Column
    public String usuario_formulario;
    @Column
    public String latitud;
    @Column
    public String longitud;

    @Column
    public String mimeType;

    @Lob
    public String fotoBase64;

    
    public Formulario() {
    }


    public Formulario( String nombre, String sector, String nivel_escolar, String latitud, String longitud, String usuario) {
        //this.id_formulario = id;
        this.nombre = nombre;
        this.sector = sector;
        this.nivel_escolar = nivel_escolar;
        this.usuario_formulario = usuario;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public Formulario( String nombre, String sector, String nivel_escolar, String latitud, String longitud, String usuario,  String tipo, String foto) {
        //this.id_formulario = id;
        this.nombre = nombre;
        this.sector = sector;
        this.nivel_escolar = nivel_escolar;
        this.usuario_formulario = usuario;
        this.latitud = latitud;
        this.longitud = longitud;
        this.mimeType = tipo;
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

