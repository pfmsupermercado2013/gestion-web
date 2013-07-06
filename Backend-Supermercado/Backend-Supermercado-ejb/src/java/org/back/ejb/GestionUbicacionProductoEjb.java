/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import javax.ejb.Stateless;
import org.back.hibernate.DAO;
import static org.back.hibernate.DAO.getSession;
import org.back.hibernate.model.UbicacionProducto;
import org.back.hibernate.model.UbicacionProductoPK;
import org.hibernate.HibernateException;

/**
 *
 * @author Fer
 */

@Stateless
public class GestionUbicacionProductoEjb extends DAO implements GestionUbicacionProductoEjbLocal {
    
    @Override
    public UbicacionProducto crearUbicacionProducto(UbicacionProducto ubiProducto)throws Exception{ 
        try {
            begin();
            getSession().save(ubiProducto);
            commit();
            DAO.close();
            return ubiProducto;
        } catch (HibernateException e) {
            throw new Exception("Error al crear la ubicacion de producto",e);
        }
    }
    
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
