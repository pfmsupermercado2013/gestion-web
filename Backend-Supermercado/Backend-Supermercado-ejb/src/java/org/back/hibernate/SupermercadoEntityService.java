/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.hibernate;

import org.back.hibernate.model.Supermercado;
import org.hibernate.HibernateException;

/**
 *
 * @author alejandrogarcia
 */
public class SupermercadoEntityService extends DAO {

    public Supermercado crearSupermercado(String nombre, String direccion, String localidad, String provincia) throws Exception {

        try {
            begin();
            Supermercado supermercado = new Supermercado(nombre, direccion, localidad, provincia);
            getSession().save(supermercado);
            commit();
            DAO.close();
            return supermercado;
        } catch (HibernateException e) {
            throw new Exception("No se ha podido crear el supermercado", e);
        }
    }
 
    public boolean borrarSupermercado(Integer idSupermercado){
        //TODO:Borrar supermercado
        return true;
    }
}