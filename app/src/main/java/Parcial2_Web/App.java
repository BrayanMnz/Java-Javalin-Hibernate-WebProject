
package Parcial2_Web;

import java.sql.SQLException;

import Parcial2_Web.util.*;
import Parcial2_Web.Classes.*;
import Parcial2_Web.Controllers.*;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;
import io.javalin.http.staticfiles.Location;


public class App {

    private static String modoConexion = "";
    public static void main(String[] args) throws SQLException{

  //El contexto SOAP debe estar creando antes de inicio del servidor.
 
            
        if(args.length >= 1){
            modoConexion = args[0];
            System.out.println("Modo de Operacion: "+modoConexion);
        }

        //Iniciando la base de datos.
        if(modoConexion.isEmpty()) {
            DataBaseServices.getInstancia().startDB(); }

            DataBaseServices.getInstancia().testConn();

            Usuario tmp = new Usuario("admin", "admin", "Default Admin", "Administrador");
            UsuarioServicios.getInstance().crear(tmp);



           //Creando la instancia del servidor.
           Javalin app = Javalin.create(conf ->{
            conf.wsFactoryConfig(ws -> { ws.getPolicy().setMaxTextMessageSize(5000000); });
            conf.registerPlugin(new RouteOverviewPlugin("rutas")); //Aplicamos el plugin de rutas
           // conf.addStaticFiles("src/main/resources/publico", Location.EXTERNAL); //desde la carpeta de resources
            
            });

            new SoapControlador(app).aplicarRutas();

            app.start(7000);

            app.after(ctx -> {
                //System.out.println("Enviando el header de seguridad para el Service Worker");
                ctx.header("Service-Worker-Allowed", "/");
            });

            //creando el manejador
            System.out.println("\n\nServer started at Port:  7000\n\n");
            app.get("/", ctx -> ctx.redirect("/Home"));
    
    
            // new UsersController(app).aplicarRutas();
            new mainController(app).aplicarRutas();
            new RestApi(app).aplicarRutas();
           
    }

    public static String getModoConexion(){
        return modoConexion;
    }

}
