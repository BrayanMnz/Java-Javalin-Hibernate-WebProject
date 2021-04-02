package Parcial2_Web.Controllers;

import Parcial2_Web.util.*;
import Parcial2_Web.Classes.*;

import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;
import java.util.*;
import static io.javalin.apibuilder.ApiBuilder.*;

public class mainController extends BaseControlador {

    private UsuarioServicios serviciosUsuarios = UsuarioServicios.getInstance();
    private FormularioServicios serviciosFormularios = FormularioServicios.getInstance();

    public mainController(Javalin app) {
        super(app);
        registrandoPlantillas();
    }

    /**
     * Registrando los sistemas de plantillas utilizados.
     */
    private void registrandoPlantillas(){
        //registrando los sistemas de plantilla.
        JavalinRenderer.register(JavalinThymeleaf.INSTANCE, ".html");
    }

  

    @Override
    public void aplicarRutas() {
        app.routes(() -> {

            before(ctx -> {

            
            });

            path("/Principal", () ->{

                get("/RegistrarPersona", ctx -> {
                    Map<String, Object> modelo = new HashMap<>();
                    ctx.render("/publico/Formulario.html",modelo);


                });

                post("/RegistrarPersona", ctx -> {
                    
                    String nombre = ctx.formParam("nombre");
                    String apellido = ctx.formParam("apellidos");
                    String sector = ctx.formParam("sector");
                    String nivelEscolar = ctx.formParam("nivelEscolar");
                    String longitud = ctx.formParam("longitud");
                    String latitud = ctx.formParam("latitud");

                    Formulario auxFormulario = new Formulario(nombre+apellido,sector,nivelEscolar,latitud,longitud);
                    serviciosFormularios.crear(auxFormulario);


                  
                });


                get("/Login", ctx-> {
                    Map<String, Object> modelo = new HashMap<>();
                    ctx.render("/publico/Login.html",modelo);
                });

               
                


                });

            });
        }
}