
package Parcial2_Web;

import java.sql.SQLException;

import Parcial2_Web.util.*;
import Parcial2_Web.Classes.*;
import Parcial2_Web.Controllers.*;
import io.javalin.Javalin;
import io.javalin.core.util.RouteOverviewPlugin;


public class App {

    private static String modoConexion = "";
    public static void main(String[] args) throws SQLException{


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
            conf.addStaticFiles("/publico"); //desde la carpeta de resources
            }).start(7000);

            //creando el manejador
            System.out.println("\n\nServer started at Port:  7000\n\n");
            app.get("/", ctx -> ctx.redirect("/Home"));
    
    
            // new UsersController(app).aplicarRutas();
            new mainController(app).aplicarRutas();
            
    }

    public static String getModoConexion(){
        return modoConexion;
    }

}
