/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.back.hibernate;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author ÓscarJavier

 
/**
 *
 * @author John
 */
public class EntityFactory {
 
    private static final SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
    private static final ThreadLocal session = new ThreadLocal();
    
    public static Session getSession(){
        Session newSession = (Session) EntityFactory.session.get();
        if(newSession == null){
            newSession = sessionFactory.openSession();
            EntityFactory.session.set(newSession);
        }
        return newSession;
    }   
    
}
