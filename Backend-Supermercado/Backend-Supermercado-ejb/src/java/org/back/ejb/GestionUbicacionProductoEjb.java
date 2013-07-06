/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import javax.ejb.Stateless;
import org.back.hibernate.DAO;
import static org.back.hibernate.DAO.getSession;
import org.back.hibernate.model.Producto;
import org.back.hibernate.model.UbicacionProducto;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author Fer
 */

@Stateless
public class GestionUbicacionProductoEjb extends DAO implements GestionUbicacionProductoEjbLocal {
    
    
    @Override
    public UbicacionProducto buscarUbicacionProducto(Integer idProducto) throws Exception
    {
        UbicacionProducto ubicacionProd = null;
            begin();
            ubicacionProd = (UbicacionProducto)getSession().createQuery("FROM UbicacionProducto u WHERE u.ubicacionProductoPK.idproducto = :idproducto").setParameter("idproducto", + idProducto).uniqueResult();
            DAO.close();
        
        return ubicacionProd; 
    }

}
