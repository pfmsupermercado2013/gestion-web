/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.hibernate;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author ÓscarJavier
 */
public class DAO {

    private static final SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private static final ThreadLocal session = new ThreadLocal();

    public static Session getSession() {
        Session newSession = (Session) DAO.session.get();
        if (newSession == null) {
            newSession = sessionFactory.openSession();
            DAO.session.set(newSession);
        }
        return newSession;
    }

    protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        try {
            getSession().close();
        } catch (HibernateException e) {
            e.printStackTrace();
        }

        DAO.session.set(null);
    }
    
    public static void close(){
        getSession().close();
        DAO.session.set(null);
    }
}
