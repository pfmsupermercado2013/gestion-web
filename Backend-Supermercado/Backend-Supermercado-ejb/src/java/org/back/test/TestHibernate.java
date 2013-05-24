/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.test;

/**
 *
 * @author ÓscarJavier
 */
import org.back.hibernate.model.Empleado;
import org.back.hibernate.model.Supermercado;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
 
/**
 *
 * @author John
 */
public class TestHibernate {
 
    private static SessionFactory sessionFactory = null;
 
    public static void main(String[] args) {
        Session session = null;
        try {
            try {
                sessionFactory = EntityFactory.getSessionFactory();
                session = sessionFactory.openSession();
 
                System.out.println("Insertando registro");
                Transaction tx = session.beginTransaction();
                
                Supermercado supermercado = new Supermercado();
                supermercado.setNombreSupermercado("DIA");
                supermercado.setDireccionSupermercado("C/ Alpujarras 24");
                supermercado.setLocalidadSupermercado("Madrid");
                supermercado.setProvinciaSupermercado("Madrid");
                //Guardando
                session.save(supermercado);
                
                //Creando un Objeto Empleado
                Empleado empleado = new Empleado();
                empleado.setSupermercado(supermercado);
                empleado.setNif("X9253124V");
                empleado.setNombreEmpleado("Javier");
                empleado.setApellidosEmpleado("Fúquene");
                empleado.setRol("Administrador");
                //Guardando
                session.save(empleado);
                tx.commit();
                System.out.println("Finalizado...");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } finally {
            session.close();
        }
    }
}