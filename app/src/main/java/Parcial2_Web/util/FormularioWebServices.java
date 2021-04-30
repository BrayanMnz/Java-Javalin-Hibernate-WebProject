package Parcial2_Web.util;


import Parcial2_Web.Classes.*;
import Parcial2_Web.util.SoapServices;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.*;

/**
 * Clase para implementar un servicio web basado en SOAP
 */
@WebService
public class FormularioWebServices {

    private SoapServices serviciosFormularios = SoapServices.getInstance();

    @WebMethod
    public String holaMundo(String hola){
        System.out.println("Ejecuntado en el servidor.");
        return "Hola Mundo "+hola+", :-D";
    }

    @WebMethod
    public String otroMetodo(String hola){
        System.out.println("Ejecuntado en el servidor.");
        return "Hola Mundo "+hola+", :-D";
    }

    @WebMethod
    public List<Formulario> getListaFormularios(){
        return  serviciosFormularios.findAll();
    }

    // @WebMethod
    // public Formulario crearFormulario(Formulario Formulario){
    //     return serviciosFormularios.crear(Formulario);
    // }

}
