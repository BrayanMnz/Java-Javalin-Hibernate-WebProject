package Parcial2_Web.Controllers;

import Parcial2_Web.util.*;
import Parcial2_Web.Classes.*;

import io.javalin.Javalin;
import io.javalin.plugin.rendering.JavalinRenderer;
import io.javalin.plugin.rendering.template.JavalinThymeleaf;
import java.util.*;
import java.util.ArrayList;
import static io.javalin.apibuilder.ApiBuilder.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.websocket.api.Session;
import java.io.IOException;

public class mainController extends BaseControlador {

    private UsuarioServicios serviciosUsuarios = UsuarioServicios.getInstance();
    private FormularioServicios serviciosFormularios = FormularioServicios.getInstance();

    //ParaWebSockets
    public static List<Session> usuariosConectados = new ArrayList<>();
    public static List<Form_JSON> formsRecibidos = new ArrayList<>();

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

        app.before(ctx -> {

            for (Form_JSON formu : formsRecibidos) {
                if (formu.getNombre() != null && formu.getSector() != null && formu.getNivel_escolar() != null) {
                    Formulario formuTmp = new Formulario(formu.getNombre(), formu.getSector(), formu.getNivel_escolar(), formu.getLatitud(), formu.getLongitud(), formu.getUsuario_formulario(),formu.getMimeType(), formu.getFotoBase64());
                    if (FormularioServicios.getInstance().findByNombre(formuTmp.getNombre()).isEmpty()) {
                        FormularioServicios.getInstance().crear(formuTmp);
                    }
                }
            }

    });

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
                    modelo.put("usuario",auxUser);
                    ctx.render("/publico/Formulario.html",modelo);
                    

                });

                post("/RegistrarPersona", ctx -> {
                    
                    String nombre = ctx.formParam("nombre");
                    String apellido = ctx.formParam("apellidos");
                    String sector = ctx.formParam("sector");
                    String Nivel_escolar = ctx.formParam("Nivel_escolar");
                    String longitud = ctx.formParam("longitud");
                    String latitud = ctx.formParam("latitud");

                    Usuario user = ctx.sessionAttribute("usuario");
                    System.out.println(user);
                    String auxUser = user.username;
                    System.out.println(auxUser);

                    Formulario auxFormulario = new Formulario(nombre+apellido,sector,Nivel_escolar,latitud,longitud,auxUser);
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

                   

                    if(serviciosUsuarios.verify_user(user,passwrd)){
                        ctx.sessionAttribute("usuario",UsuarioServicios.getInstance().getUsuario(user));
                        ctx.redirect("/Principal/RegistrarPersona");
                       
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

                app.ws("/mensajeServidor", ws -> {

                    ws.onConnect(ctx -> {
                        System.out.println("Conexión Iniciada - "+ctx.getSessionId());
                        usuariosConectados.add(ctx.session);
                    });
        
                    ws.onMessage(ctx -> {

                        System.out.println("Llegó un msj");
                        ctx.headerMap();
                        ctx.pathParamMap();
                        ctx.queryParamMap();
                        boolean condicion = true;
                        Form_JSON tempFormu = jacksonToObject(ctx.message());
                        for(Form_JSON formu : formsRecibidos){
                            if(tempFormu.getId_formulario() == formu.getId_formulario()){
                                condicion = false;
                            }
                        }
                        if(condicion){
                            formsRecibidos.add(tempFormu);
                        }
        
                        //
                        System.out.println("Mensaje Recibido de "+ctx.getSessionId()+" ====== ");
                        System.out.println("Mensaje: "+ ctx.message());
                        System.out.println("================================");



                        for (Form_JSON formu : formsRecibidos) {
                            if (formu.getNombre() != null && formu.getSector() != null && formu.getNivel_escolar() != null) {
                                Formulario formuTmp = new Formulario(formu.getNombre(), formu.getSector(), formu.getNivel_escolar(), formu.getLatitud(), formu.getLongitud(), formu.getUsuario_formulario(), formu.getMimeType(), formu.getFotoBase64());
                                if (FormularioServicios.getInstance().findByNombre(formuTmp.getNombre()).isEmpty()) {
                                    FormularioServicios.getInstance().crear(formuTmp);
                                }
                            }
                        }
                        //
                    });
        
                    ws.onBinaryMessage(ctx -> {
                        System.out.println("Mensaje Recibido Binario "+ctx.getSessionId()+" ====== ");
                        System.out.println("Mensaje: "+ctx.data().length);
                        System.out.println("================================");
                    });
        
                    ws.onClose(ctx -> {

                        System.out.println("Conexión Cerrada - "+ctx.getSessionId());
                        usuariosConectados.remove(ctx.session);
                    });
        
                    ws.onError(ctx -> {
                        System.out.println("Ocurrió un error en el WS");
                    });
                });

                app.wsAfter("/mensajeServidor",ws -> {
                    // runs after all WebSocket requests

                    //System.out.println("Insertando data en Base de Datos...");
                    
                    try {
                        for (Form_JSON formu : formsRecibidos) {
                            if (formu.getNombre() != null && formu.getSector() != null && formu.getNivel_escolar() != null && formu.getMimeType() != null && formu.getFotoBase64() != null) {
                                Formulario formuTmp = new Formulario(formu.getNombre(), formu.getSector(), formu.getNivel_escolar(), formu.getLatitud(), formu.getLongitud(), formu.getUsuario_formulario(),formu.getMimeType(), formu.getFotoBase64());
                                if (FormularioServicios.getInstance().findByNombre(formuTmp.getNombre()).isEmpty()) {
                                    FormularioServicios.getInstance().crear(formuTmp);
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println("Ocurrió un error insertando la información en la Base de Datos.\n"+e);
                    }


                    



                });

            });


        }

        public static Form_JSON jacksonToObject(String jsonString)
        throws IOException{
         ObjectMapper mapper = new ObjectMapper();

    return mapper.readValue(jsonString, Form_JSON.class);
}

}