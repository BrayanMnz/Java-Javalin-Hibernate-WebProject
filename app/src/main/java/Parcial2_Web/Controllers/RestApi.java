package Parcial2_Web.Controllers;

import Parcial2_Web.util.FormularioServicios;
import io.javalin.Javalin;
import io.javalin.http.ForbiddenResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.security.Keys;

import static io.javalin.apibuilder.ApiBuilder.*;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.*;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import Parcial2_Web.util.*;
import Parcial2_Web.Classes.*;
import Parcial2_Web.Classes.LoginResponse;

public class RestApi extends BaseControlador {

    public final static String ACCEPT_TYPE_JSON = "application/json";
    public final static String ACCEPT_TYPE_XML = "application/xml";
    public final static int BAD_REQUEST = 400;
    public final static int UNAUTHORIZED = 401;
    public final static int FORBIDDEN = 403;
    public final static int ERROR_INTERNO = 500;


    public final static String SECRET_KEY = "ghQaYY7Wo24sDqKSX3IM9ASGmdGPmkTd9jo1QTy4b7P9Ze5_9hKolVX8xNrQDcNRfVEdTZNOuOyqEGhXEbdJI-ZQ19k_o9MI0y3eZN2lp9jow55FfXMiINEdt1XR85VipRLSOkT6kSpzs2x-jbLDiz9iFVzkd81YKxMgPA7VfZeQUm4n-mOmnWMaVX30zGFU4L3oPBctYKkl4dYfqYWqRNfrgPJVi5DGFjywgxx0ASEiJHtV72paI3fDR2XwlSkyhhmY-ICjCRmsJN4fX1pdoL8a18-aQrvyu4j0Os6dVPYIoPvvY0SAZtWYKHfM15g7A3HD4cVREf9cUsprCRK93w";
    
    private FormularioServicios serviciosFormularios = FormularioServicios.getInstance();
    private UsuarioServicios serviciosUsuarios = UsuarioServicios.getInstance();

    public boolean inicio = false;
    public Usuario global_Usuario = null;
    

    public RestApi(Javalin app) {
        super(app);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void aplicarRutas() {
        app.routes(() -> {


        path("/Principal", () ->{

            get("/Login", ctx-> {
                Map<String, Object> modelo = new HashMap<>();
                ctx.render("/publico/Login.html",modelo);
            });


            post("/Login", ctx-> {

                
                String user = ctx.formParam("usuario");
                String passwrd = ctx.formParam("password");

                if(user == null && passwrd == null){
                    user = ctx.queryParam("usuario");
                    passwrd = ctx.queryParam("password");
                }

                System.out.println(user);

                System.out.println(passwrd);

                if(serviciosUsuarios.verify_user(user,passwrd)){
                    System.out.println("si soy yo");
                    inicio = true;
                   
                   Usuario usuario = UsuarioServicios.getInstance().getUsuario(user);
                   global_Usuario = usuario;
                   ctx.json(generacionJsonWebToken(usuario));
                   ctx.sessionAttribute("usuario",usuario);
                //    ctx.redirect("/Principal/RegistrarPersona");
                   System.out.println("Lo genero");

                } else {
                    System.out.println("USUARIO NO EXISTEE!");
                    ctx.render("/publico/usuario_no.html");
                    

                }


            });

        });

            path("/api", () -> {
              
                path("/formulario", () -> {

                    before("/", ctx -> {

                        //Si es por el metodo OPTIONS lo dejo pasar.

                        if(ctx.method() == "OPTIONS"){
                        return;
                        }

                        //informacion para consultar en la trama.

                        String header = "Authorization";
                        String prefix = "Bearer";
                        String headerAutentification = ctx.header(header);

                        if(headerAutentification ==null || !headerAutentification.startsWith(prefix)){
                    
                        throw new ForbiddenResponse( "No tiene permiso para acceder!! ");

                        }
 
                        String tramaJwt = headerAutentification.replace(prefix, "");

                        try {

                        Claims claims = Jwts.parser()

                        .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))

                        .parseClaimsJws(tramaJwt).getBody();

                        //mostrando la información para demostración.
 
                        System.out.println("Mostrando el JWT recibido: " + claims.toString());
 
                        }catch (ExpiredJwtException | MalformedJwtException | SignatureException e){ //Excepciones comunes
 
                        throw new ForbiddenResponse( ((Throwable) e).getMessage());

                        }

                        });


                        after("/", ctx -> {
                            String headerAuth = ctx.req.getHeader("Authorization");
                            if(headerAuth != null){
                                // System.out.println("JWT Recibido" + decodeJWT(headerAuth));
                            }
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

                });
            });
        });

        
    }

    // public static String createJWT(String username) {

    //     //The JWT signature algorithm we will be using to sign the token
    //     SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    //     long nowMillis = System.currentTimeMillis();
    //     Date FechaActual = new Date(nowMillis);

    //     //We will sign our JWT with our ApiKey secret
    //     byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SECRET_KEY);
    //     Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

    //     //Let's set the JWT Claims
    //     JwtBuilder builder = Jwts.builder().setId(username)
    //             .setIssuedAt(FechaActual)
    //             .setSubject("Proyecto Final, Programacion Web")
    //             .setIssuer("Brayan Muñoz 2017-0770 ")
    //             .signWith(signatureAlgorithm, signingKey);

    //     // 5 minutes para expiracion
    //     long expMillis = nowMillis + 300000;
    //     Date exp = new Date(expMillis);
    //     builder.setExpiration(exp);

    //     //Builds the JWT and serializes it to a compact, URL-safe string
    //     return builder.compact();
    // }

    // public static Claims decodeJWT(String jwt) {

    //     //This line will throw an exception if it is not a signed JWS (as expected)
    //     Claims claims = Jwts.parser()
    //             .setSigningKey(DatatypeConverter.parseBase64Binary(SECRET_KEY))
    //             .parseClaimsJws(jwt).getBody();
    //     return claims;
    // }





    private static LoginResponse generacionJsonWebToken(Usuario usuario){
        LoginResponse loginResponse = new LoginResponse();
        //generando la llave.
        SecretKey secretKey = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        //Generando la fecha valida
        LocalDateTime localDateTime = LocalDateTime.now().plusMinutes(60);
        // System.out.println("La fecha actual: "+localDateTime.toString());

        //
        Date fechaExpiracion = Date.from(localDateTime.toInstant(ZoneOffset.ofHours(-4)));
        // creando la trama.
        String jwt = Jwts.builder()
                .setIssuer("Brayan Muñoz 2017-0770 ")
                .setSubject("Proyecto Final, Programacion Web")
                .setExpiration(fechaExpiracion)
                .claim("usuario", usuario.getusername())
                .signWith(secretKey)
                .compact();
        loginResponse.setToken(jwt);
        loginResponse.setExpires_in(fechaExpiracion.getTime());
        System.out.println(loginResponse);
        return loginResponse;
    }

}