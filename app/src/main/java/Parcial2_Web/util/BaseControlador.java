package Parcial2_Web.util;

import io.javalin.Javalin;

public abstract class BaseControlador {

    protected Javalin app;

    public BaseControlador(Javalin app){
        this.app = app;
    }

    abstract public void aplicarRutas();
}