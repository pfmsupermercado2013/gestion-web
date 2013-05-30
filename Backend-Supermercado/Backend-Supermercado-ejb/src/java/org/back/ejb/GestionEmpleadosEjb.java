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
import org.back.hibernate.DAO;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ÓscarJavier
 */
@Stateful
public class GestionEmpleadosEjb extends DAO implements GestionEmpleadosEjbLocal {
        
    @Override
    public boolean CrearEmpleado(Empleado empleado) {
        boolean operacionOk = false;
        Session session = null;
        session = DAO.getSession();
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
