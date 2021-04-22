package Parcial2_Web.util;

import Parcial2_Web.Classes.*;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import java.util.*;

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

    public List<Formulario> findByNombre(String nombre) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT f FROM Formulario f where f.nombre = :nombre");
        query.setParameter("nombre", nombre);
        List<Formulario> lista = query.getResultList();
        return lista;
    }

    public List<Formulario> findByUsuario(String usuario) {
        EntityManager em = getEntityManager();
        Query query = em.createQuery("SELECT f FROM Formulario f where f.usuario_formulario = :usuario");
        query.setParameter("usuario", usuario);
        List<Formulario> lista = query.getResultList();
        return lista;
    }
    
}
