package Parcial2_Web.util;

import Parcial2_Web.Classes.Usuario;

public class UsuarioServicios extends GestionDb<Usuario>  {
    private static UsuarioServicios instance;

    public UsuarioServicios() {
        super(Usuario.class);
    }

    public static UsuarioServicios getInstance() {
        if(instance==null){
            instance = new UsuarioServicios();
        }
        return instance;
    }
    public Usuario getUsuario(String username){
        return find(username);
    }
    public boolean verify_user(String username, String password){

        try {
            Usuario aux = find(username);
            if (aux.getusername().equalsIgnoreCase(username) && aux.getPasswd_usuario().equals(password)){
                return true;
            }else {
                return false;
            }
        }catch (Exception e){
            return false;
        }
    }
    
}
