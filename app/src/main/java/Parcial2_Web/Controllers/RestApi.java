package Parcial2_Web.Controllers;

import Parcial2_Web.util.FormularioServicios;
import io.javalin.Javalin;
import static io.javalin.apibuilder.ApiBuilder.*;
import Parcial2_Web.util.*;
import Parcial2_Web.Classes.*;

public class RestApi extends BaseControlador {
    
    private FormularioServicios serviciosFormularios = FormularioServicios.getInstance();

    public RestApi(Javalin app) {
        super(app);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void aplicarRutas() {
        app.routes(() -> {
            path("/api", () -> {
                /**
                 * Ejemplo de una API REST, implementando el CRUD
                 * ir a
                 */
                path("/formulario", () -> {
                    after(ctx -> {
                        ctx.header("Content-Type", "application/json");
                    });

                    get("/", ctx -> {
                        ctx.json(serviciosFormularios.findAll());
                    });

                    get("/:usuario", ctx -> {
                        ctx.json(serviciosFormularios.findByUsuario(ctx.pathParam("usuario", String.class).get()));
                    });

                    post("/", ctx -> {
                        //parseando la informacion del POJO debe venir en formato json.
                        Formulario tmp = ctx.bodyAsClass(Formulario.class);
                        //creando.
                        ctx.json(serviciosFormularios.crear(tmp));
                    });

                    // put("/", ctx -> {
                    //     //parseando la informacion del POJO.
                    //     Estudiante tmp = ctx.bodyAsClass(Estudiante.class);
                    //     //creando.
                    //     ctx.json(fakeServices.actualizarEstudiante(tmp));

                    // });

                    // delete("/:matricula", ctx -> {
                    //     //creando.
                    //     ctx.json(fakeServices.eliminandoEstudiante(ctx.pathParam("matricula", Integer.class).get()));
                    // });
                });
            });
        });

        // app.exception(NoExisteEstudianteException.class, (exception, ctx) -> {
        //     ctx.status(404);
        //     ctx.json(""+exception.getLocalizedMessage());
        // });
    }


}