package Parcial2_Web.util;


import Parcial2_Web.Classes.*;
import Parcial2_Web.util.SoapServices;
import javax.persistence.Query;
import com.google.gson.Gson;
import javax.jws.WebMethod;
import javax.jws.WebService;
import java.util.*;

/**
 * Clase para implementar un servicio web basado en SOAP
 */
@WebService
public class FormularioWebServices {

    // private SoapServices serviciosFormularios = SoapServices.getInstance();
    private FormularioServicios serviciosFormularios = FormularioServicios.getInstance();

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
    public String ListarFormularios(){

        // Usuario usuario = UsuarioServices.getInstancia().find(user);
        
        List<Formulario> lista = serviciosFormularios.findAll();

        Gson listaFormularios = new Gson();

        return listaFormularios.toJson(lista);

    }


    @WebMethod
    public String FormulariosPorUsuario(String user){

        // Usuario usuario = UsuarioServices.getInstancia().find(user);
        
        List<Formulario> lista = serviciosFormularios.findByUsuario(user);

        Gson listaFormularios = new Gson();

        return listaFormularios.toJson(lista);

    }

    @WebMethod
    public void crearFormulario(String tempFormu){

        Gson gson = new Gson();
        Formulario formu = new Formulario();
        formu = gson.fromJson(tempFormu, Formulario.class);
        serviciosFormularios.crear(formu);
    }

}
