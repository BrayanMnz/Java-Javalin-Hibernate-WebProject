package Parcial2_Web.Controllers;

import Parcial2_Web.Classes.Foto;

public class FotoControllers extends DBManage<Foto> {

    private static FotoControllers instancia;

    public FotoControllers() {
        super(Foto.class);
    }
    public static FotoControllers getInstance(){
        if(instancia==null){
            instancia = new FotoControllers();
        }

        return instancia;
    }
}
