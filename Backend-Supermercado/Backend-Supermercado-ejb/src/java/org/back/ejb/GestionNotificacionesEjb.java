/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import java.util.List;
import javax.ejb.Stateless;
import org.back.hibernate.DAO;
import static org.back.hibernate.DAO.getSession;
import org.back.hibernate.model.Notificaciones;
import org.hibernate.HibernateException;
import org.hibernate.Query;

/**
 *
 * @author ÓscarJavier
 */
@Stateless
public class GestionNotificacionesEjb extends DAO implements GestionNotificacionesEjbLocal
{
    @Override
    public List<Notificaciones> listarNotificaciones() throws Exception {
        List<Notificaciones> listaCategorias = null;
        try {
            begin();
            Query query = getSession().createQuery("FROM Notificaciones");
            //query.setMaxResults();
            listaCategorias = query.list();
            DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al listar las notificaciones",e);
        }
        
        return listaCategorias;
    }
}
