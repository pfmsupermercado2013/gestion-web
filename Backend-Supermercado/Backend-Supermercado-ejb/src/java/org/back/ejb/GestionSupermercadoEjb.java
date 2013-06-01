/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import org.back.hibernate.DAO;
import static org.back.hibernate.DAO.getSession;
import org.back.hibernate.model.Supermercado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.back.hibernate.model.Supermercado;
import org.hibernate.HibernateException;


/**
 *
 * @author ÓscarJavier
 */
@Stateless
public class GestionSupermercadoEjb extends DAO implements GestionSupermercadoEjbLocal {
    private static SessionFactory sessionFactory = null;
      
    @Override
    public Supermercado crearSupermercado(Supermercado supermercado) throws Exception{
       try {
            begin();
            getSession().save(supermercado);
            commit();
            DAO.close();
            return supermercado;
        } catch (HibernateException e) {
            throw new Exception("Error al crear el supermercado",e);
        }
    }
    
    public List<Supermercado> listarSupermercados() {
        List<Supermercado> listaSupermercados = null;
        Session session = null;
        Transaction tx = null;
        session =  this.getSession();
        try{
             tx = session.beginTransaction();
             // Obtenemos los supermercados existentes
             listaSupermercados = (List<Supermercado>)session.createQuery("FROM supermercado").list();
        }catch(Exception ex){
            if(tx != null) tx.rollback();
            ex.printStackTrace();
        } finally {
            if(session!= null) session.close();
        }
        
        return listaSupermercados;
    }
}
