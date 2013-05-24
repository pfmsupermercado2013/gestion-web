/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateful;
import org.back.hibernate.model.Empleado;
import org.back.hibernate.model.Supermercado;
import org.back.test.EntityFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ÓscarJavier
 */
@Stateful
public class GestionEmpleadosEjb implements GestionEmpleadosEjbLocal {
    
    private static SessionFactory sessionFactory = null;
    
    @Override
    public boolean CrearEmpleado(Empleado empleado) {
        boolean operacionOk = false;
        Session session = null;
        sessionFactory = EntityFactory.getSessionFactory();
        session = sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        // Obtenemos el supermercado al que pertenece el empleado
        List listSupermercado = session.createQuery("FROM supermercado").list();
        for(Iterator iterator = listSupermercado.iterator(); iterator.hasNext();){
          Supermercado supermercado = (Supermercado) iterator.next();
        }
        
        return operacionOk;
    }

    @Override
    public boolean EditarEmpleado(String idEmpleado) {
        return false;
    }
    
}
