/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.ejb;

import java.util.Iterator;
import java.util.List;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import org.back.hibernate.model.Empleado;
import org.back.hibernate.model.Supermercado;
import org.back.hibernate.DAO;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author ÓscarJavier
 */
@Stateless
public class GestionEmpleadosEjb extends DAO implements GestionEmpleadosEjbLocal {
        
    @Override
    public Empleado crearEmpleado(Empleado empleado) throws Exception {
        try{
            begin();
            getSession().save(empleado);
            commit();
            DAO.close();
        return empleado; 
        } catch (HibernateException e) {
            throw new Exception("Error al crear el empleado",e);
        }
    }

    @Override
    public boolean editarEmpleado(String idEmpleado) {
        return false;
    }

    @Override
    public Empleado validarIdentidadEmpleado(String idEmpleado, String password) throws Exception{
        List<Empleado> listaEmpleado = null;
        Empleado empleado = null;
        try{
            begin();
            // Buscamos al empleado de acuerdo a su identificador y password
            listaEmpleado = getSession().createQuery("FROM Empleado e WHERE e.nif = '"+ idEmpleado +"' ").list();
            
            if(listaEmpleado!= null && !listaEmpleado.isEmpty()){
                Iterator it = listaEmpleado.iterator();
                while(it.hasNext()){
                    empleado = (Empleado)it.next();
                }
                // Si las contraseñas no son iguales devolvemos nulo
                if(!empleado.getPassword().equals(password)){
                    empleado = null;
                }
            }
            DAO.close();
        } catch (HibernateException e) {
            throw new Exception("Error al validar identidad del empleado",e);
        }
        return empleado;
    }
    
    
    
}
