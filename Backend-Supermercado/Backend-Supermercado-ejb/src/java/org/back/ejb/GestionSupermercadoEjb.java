/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateful;
import org.back.hibernate.DAO;
import org.back.hibernate.model.Supermercado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.back.hibernate.model.Supermercado;


/**
 *
 * @author ÓscarJavier
 */
@Stateful
public class GestionSupermercadoEjb extends DAO implements GestionSupermercadoEjbLocal {
    private static SessionFactory sessionFactory = null;
    
    @Override
    public boolean CrearSupermercado(Supermercado supermercado) {
        boolean operacion = false;
        Session session = null;
        Transaction tx = null;
        session =  this.getSession();
        
        try{
             tx = session.beginTransaction();
             // Guardamos el supermercado al que pertenece el empleado
             session.save(supermercado);
             //this.close();
             operacion = true;
        }catch(Exception ex){
            if(tx != null) tx.rollback();
            ex.printStackTrace();
        } finally {
            if(session!= null) session.close();
        }
        
        return operacion;
    }
    
    public List<Supermercado> ListarSupermercados() {
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
