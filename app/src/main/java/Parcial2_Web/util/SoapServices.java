package Parcial2_Web.util;

import Parcial2_Web.Classes.*;
import java.text.Normalizer;
import java.util.*;


/**
 * Ejemplo de servicio patron Singleton
 */
public class SoapServices {

    private static SoapServices instancia;

    private SoapServices(){ }

    public static SoapServices getInstance(){
        if(instancia==null){
            instancia = new SoapServices();
        }
        return instancia;
    }

    /**
     * Permite autenticar los usuarios. Lo ideal es sacar en
     * @param usuario
     * @param password
     * @return
     */
    // public Usuario autheticarUsuario(String usuario, String password){
    //     //simulando la busqueda en la base de datos.
    //     return new Usuario(usuario, "Usuario "+usuario, password, password);
    // }



    public List<Formulario> findAll(){
        return FormularioServicios.getInstance().findAll();
    }

    public Formulario getFormulario(int id){
        return FormularioServicios.getInstance().find(id);
    }

    public Formulario crearFormulario(Formulario formulario){

        FormularioServicios.getInstance().crear(formulario);
        return formulario;
    }

    // public Formulario actualizarFormulario(Formulario formulario){
    //     if (FormularioServicios.getInstance().editar(formulario)){
    //         return formulario;
    //     }

    //     return null;
    // }

    public boolean eliminandoFormulario(int id){
        return FormularioServicios.getInstance().eliminar(id);
    }

}