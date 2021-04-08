package Parcial2_Web.Controllers;

import Parcial2_Web.util.*;
import Parcial2_Web.Classes.*;

import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;
import java.util.*;
import java.util.ArrayList;
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

            before("/Principal/RegistrarPersona",ctx ->{
                if(ctx.sessionAttribute("usuario") ==null) {
                    ctx.redirect("/Principal/Login");
                }
            });

            before("/Principal/CrearUsuario",ctx ->{
                if(ctx.sessionAttribute("usuario") ==null) {
                    ctx.redirect("/Principal/Login");
                }
            });


            get("/Home", ctx -> {
                ctx.render("/publico/Home.html");
            });


            path("/Principal", () ->{

                get("/RegistrarPersona", ctx -> {
                    Map<String, Object> modelo = new HashMap<>();

                    Usuario user = ctx.sessionAttribute("usuario");
                    String auxUser = user.username;
                    System.out.println(auxUser);
                    modelo.put("usuario",auxUser);
                    ctx.render("/publico/Formulario.html",modelo);
                    

                });

                post("/RegistrarPersona", ctx -> {
                    
                    String nombre = ctx.formParam("nombre");
                    String apellido = ctx.formParam("apellidos");
                    String sector = ctx.formParam("sector");
                    String nivelEscolar = ctx.formParam("nivelEscolar");
                    String longitud = ctx.formParam("longitud");
                    String latitud = ctx.formParam("latitud");

                    Usuario user = ctx.sessionAttribute("usuario");
                    System.out.println(user);
                    String auxUser = user.username;
                    System.out.println(auxUser);

                    Formulario auxFormulario = new Formulario(nombre+apellido,sector,nivelEscolar,latitud,longitud,auxUser);
                   // serviciosFormularios.crear(auxFormulario);

                   ctx.redirect("/Principal/RegistrarPersona");
                  
                });


                get("/Login", ctx-> {
                    Map<String, Object> modelo = new HashMap<>();
                    ctx.render("/publico/Login.html",modelo);
                });


                post("/Login", ctx-> {

                    String user = ctx.formParam("username");
                    String passwrd = ctx.formParam("password");

                    System.out.println(user);
                    System.out.println(passwrd);   

                    if(serviciosUsuarios.verify_user(user,passwrd)){
                        ctx.sessionAttribute("usuario",UsuarioServicios.getInstance().getUsuario(user));
                        ctx.redirect("/Principal/RegistrarPersona");
                        System.out.println("Ay si, essss el! ");  
                    } else {
                        
                        System.out.println("USUARIO NO EXISTEE!");
                        ctx.render("/publico/usuario_no.html");
                        

                    }


                });



               
                get("/ListarFormularios", ctx-> {

                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("title","Formularios a enviar al servidor");

                    ctx.render("/publico/indexed_list.html",modelo);

                });

                get("/CrearUsuario", ctx-> {
                    Map<String, Object> modelo = new HashMap<>();
                    modelo.put("titulo", "Crear usuarios");
                    ctx.render("/publico/crear_Usuario.html",modelo);
                });


                post("/CrearUsuario", ctx-> {
                    
                    String nombreUsuario = ctx.formParam("nmbr");
                    String usrname = ctx.formParam("usrname");
                    String contrasenia = ctx.formParam("passwd");
                    String rolUser = ctx.formParam("rolUsuario");

                    Usuario auxUsuario = new Usuario(usrname,contrasenia,nombreUsuario,rolUser);
                    serviciosUsuarios.crear(auxUsuario);

                    ctx.redirect("/Principal/CrearUsuario");
                     
                });

                get("/VerUsuarios", ctx-> {
                    Map<String, Object> modelo = new HashMap<>();
                    List<Usuario> usuarios = serviciosUsuarios.getInstance().findAll();
                    System.out.println(usuarios);
                    modelo.put("titulo", "Ver usuarios");
                    modelo.put("misUsuarios",usuarios);
                    ctx.render("/publico/VerUsuario.html",modelo);
                });


                });

            });
        }
}