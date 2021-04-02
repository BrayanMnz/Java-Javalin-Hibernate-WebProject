package Parcial2_Web.util;

import Parcial2_Web.Classes.*;

public class FormularioServicios extends GestionDb<Formulario>  {
    private static FormularioServicios instance;

    public FormularioServicios() {
        super(Formulario.class);
    }

    public static FormularioServicios getInstance() {
        if(instance==null){
            instance = new FormularioServicios();
        }
        return instance;
    }


    
}
